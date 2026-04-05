package com.medisphere.doctor.service;

import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.dto.Request.UpdateDoctorDTO;
import com.medisphere.doctor.dto.Response.GetAllDoctorsDTO_patient;
import com.medisphere.doctor.entity.DoctorEntity;
import com.medisphere.doctor.exception.EntryNotFoundException;
import com.medisphere.doctor.repository.DoctorRepository;
import com.medisphere.doctor.util.MessageConstant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<GetAllDoctorsDTO_patient> getAllDoctors() {
        try {
            List<DoctorEntity> getAllDoctors = doctorRepository.findAll();
            return modelMapper.map(getAllDoctors, new TypeToken<List<GetAllDoctorsDTO_patient>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch doctors: " + e.getMessage());
        }
    }

    public GetByIdDoctorDTO getDoctorById(GetByIdDoctorDTO id) {
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

    public GetByIdDoctorDTO updateDoctorDetails(String doctorId, UpdateDoctorDTO updateDoctorDTO) {
        try {
            DoctorEntity existingDoctor = doctorRepository.findByDoctorId(doctorId);
            if (existingDoctor == null) {
                throw new EntryNotFoundException("Doctor not found with ID: " + doctorId);
            }
            existingDoctor.setDrName(updateDoctorDTO.getDrName());
            existingDoctor.setDrContactNo(updateDoctorDTO.getDrContactNo());
            existingDoctor.setStatus(updateDoctorDTO.getStatus());
            doctorRepository.save(existingDoctor);
            return modelMapper.map(existingDoctor, GetByIdDoctorDTO.class);
        } catch (EntryNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating doctor details: " + e.getMessage());
        }
    }


}
