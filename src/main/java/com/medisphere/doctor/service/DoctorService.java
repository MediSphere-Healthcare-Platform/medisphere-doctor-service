package com.medisphere.doctor.service;

import com.medisphere.doctor.dto.Request.CreateDoctorDTO;
import com.medisphere.doctor.dto.Request.DeleteDoctorDTO;
import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.dto.Request.NotificationRequestDTO;
import com.medisphere.doctor.dto.Request.UpdateDoctorDTO;
import com.medisphere.doctor.client.AuthClient;
import com.medisphere.doctor.client.NotificationClient;
import com.medisphere.doctor.dto.Response.GetAllDoctorsDTO_patient;
import com.medisphere.doctor.entity.DoctorEntity;
import com.medisphere.doctor.exception.EntryNotFoundException;
import com.medisphere.doctor.repository.DoctorRepository;
import com.medisphere.doctor.util.StandardResponse;
import feign.Param;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final NotificationClient notificationClient;
    private final AuthClient authClient;
    private final CloudinaryService cloudinaryService;

    public Long getMaxMsUserId() {
        return doctorRepository.findMaxMsUserId();
    }

    public List<GetAllDoctorsDTO_patient> getAllDoctors() {
        try {
            List<DoctorEntity> getAllDoctors = doctorRepository.getActiveDoctors();
            return modelMapper.map(getAllDoctors, new TypeToken<List<GetAllDoctorsDTO_patient>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch doctors: " + e.getMessage());
        }
    }

    public GetByIdDoctorDTO getDoctorById(@Validated GetByIdDoctorDTO id) {
        try {
            DoctorEntity doctor = doctorRepository.getDoctorByDoctorId(id);
            if (doctor == null) {
                throw new EntryNotFoundException("Doctor not found with ID: " + id.getDoctorId());
            }
            return modelMapper.map(doctor, GetByIdDoctorDTO.class);
        } catch (EntryNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving doctor: " + e.getMessage());
        }
    }

    public GetByIdDoctorDTO updateDoctorDetails(String doctorId, UpdateDoctorDTO updateDoctorDTO,
                                                 org.springframework.web.multipart.MultipartFile profileImage) {
        try {
            DoctorEntity existingDoctor = doctorRepository.findByDoctorId(doctorId);
            if (existingDoctor == null) {
                throw new EntryNotFoundException("Doctor not found with ID: " + doctorId);
            }
            existingDoctor.setFirstName(updateDoctorDTO.getFirstName());
            existingDoctor.setLastName(updateDoctorDTO.getLastName());
            existingDoctor.setDrContactNo(updateDoctorDTO.getDrContactNo());
            existingDoctor.setStatus(updateDoctorDTO.getStatus());

            // Upload new profile image to Cloudinary if provided
            if (profileImage != null && !profileImage.isEmpty()) {
                // Delete old image from Cloudinary if it exists
                if (existingDoctor.getProfilePic() != null && !existingDoctor.getProfilePic().isBlank()) {
                    cloudinaryService.deleteImage(existingDoctor.getProfilePic());
                }
                String imageUrl = cloudinaryService.uploadProfileImage(profileImage);
                existingDoctor.setProfilePic(imageUrl);
            }

            existingDoctor.setModifiedDate(java.time.Instant.now());
            doctorRepository.save(existingDoctor);

            return modelMapper.map(existingDoctor, GetByIdDoctorDTO.class);

        } catch (EntryNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating doctor details: " + e.getMessage());
        }
    }

    public String CreateDoctor(@Validated CreateDoctorDTO createDoctorDTO) {
        try {

            DoctorEntity existingDoctor = doctorRepository.findByDoctorId(createDoctorDTO.getDoctorId());

            if(existingDoctor != null) {
                throw new RuntimeException("Doctor already exists");
            }

            DoctorEntity msUserIdExist = doctorRepository.getDoctorByMsUserId(createDoctorDTO.getMsUserId());

            if(msUserIdExist != null) {
                throw new RuntimeException("User already registered as a doctor");
            }

            DoctorEntity nicExist = doctorRepository.findDoctorByNIC(createDoctorDTO.getDrNic());

            if(nicExist != null) {
                throw new RuntimeException("The Given NIC already registered");
            }

            DoctorEntity doctor = new DoctorEntity();

            doctor.setFirstName(createDoctorDTO.getFirstName());
            doctor.setLastName(createDoctorDTO.getLastName());
            doctor.setDrContactNo(createDoctorDTO.getDrContactNo());
            doctor.setStatus(createDoctorDTO.getStatus());
            doctor.setDoctorId(createDoctorDTO.getDoctorId());
            doctor.setDrNic(createDoctorDTO.getDrNic());
            doctor.setDrLicence(createDoctorDTO.getDrLicence());
            doctor.setMsUserId(createDoctorDTO.getMsUserId());
            doctor.setProfilePic(createDoctorDTO.getProfilePic());
            doctor.setSpecialty(createDoctorDTO.getSpecialty());

            //set time stamp
            Instant now = Instant.now();
            doctor.setCreateDate(now);
            doctor.setModifiedDate(now);

            doctorRepository.save(doctor);
            return "Successful";

        }catch (Exception e) {
            throw new RuntimeException("Error Creating doctor: " + e.getMessage());
        }
    }

    public String DeleteDoctor(DeleteDoctorDTO deleteDoctorDTO) {
        try {
            String doctorId = deleteDoctorDTO.getDoctorId();
            if (doctorId == null || doctorId.equalsIgnoreCase("null") || doctorId.isEmpty()) {
                throw new RuntimeException("Invalid Doctor ID");
            }

            // 1. Fetch doctor info before deletion to capture msUserId
            DoctorEntity doctor = doctorRepository.findByDoctorId(doctorId);
            if (doctor == null) {
                throw new EntryNotFoundException("Doctor not found");
            }
            String msUserId = doctor.getMsUserId();

            // 2. Perform deletion
            doctorRepository.deleteByDoctorId(doctorId);

            // Sync deletion with Auth Service
            try {
                authClient.deleteUserByMsUserId(msUserId);
            } catch (Exception e) {
                System.err.println("Failed to sync deletion with Auth Service: " + e.getMessage());
            }

            // 3. Send Notification (Best-effort, wrapped in try-catch)
            try {
                NotificationRequestDTO notificationRequest = NotificationRequestDTO.builder()
                        .userId(msUserId)
                        .userRole("DOCTOR")
                        .title("Account Deleted")
                        .message("Your MediSphere doctor account (ID: " + doctorId + ") has been successfully deleted. Thank you for your service.")
                        .channel("EMAIL")
                        .isBroadcast(false)
                        .build();

                System.out.println("Sending deletion notification to: " + msUserId);
                notificationClient.createNotification(notificationRequest);
            } catch (Exception e) {
                // Log the error but don't fail the deletion if the notification service is down
                System.err.println("Notification trigger failed: " + e.getMessage());
            }

            return "Deleted";
        } catch (Exception e) {
            throw new RuntimeException("Error Deleting doctor: " + e.getMessage());
        }
    }


}
