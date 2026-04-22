package com.medisphere.doctor.controller;

import com.medisphere.doctor.client.AppointmentClient;
import com.medisphere.doctor.client.PatientClient;
import com.medisphere.doctor.client.TelemedicineClient;
import com.medisphere.doctor.dto.Request.*;
import com.medisphere.doctor.dto.Telemedicine.CreateSessionRequestDTO;
import com.medisphere.doctor.dto.Telemedicine.PrescriptionRequestDTO;
import com.medisphere.doctor.dto.Telemedicine.PrescriptionUpdateRequestDTO;
import com.medisphere.doctor.dto.Telemedicine.TelemedicineEndSessionRequestDTO;
import com.medisphere.doctor.dto.Response.GetAllDoctorsDTO_patient;
import com.medisphere.doctor.repository.DoctorRepository;
import com.medisphere.doctor.service.DoctorService;
import com.medisphere.doctor.util.Endpoint;
import com.medisphere.doctor.util.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor/api/v1")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentClient appointmentClient;
    private final PatientClient  patientClient;
    private final TelemedicineClient telemedicineClient;

    @GetMapping(value = Endpoint.GET_ALL_DOCTORS_FOR_PATIENT)
    public ResponseEntity<StandardResponse> getAllDoctors() {
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctors retrieved successfully", doctorService.getAllDoctors()),
                HttpStatus.OK
        );
    }

    @GetMapping(value = Endpoint.GET_DOCTOR_BY_ID)
    public ResponseEntity<StandardResponse> getDoctorById(@Valid @PathVariable("id") String id) {
        GetByIdDoctorDTO dto = new GetByIdDoctorDTO();
        dto.setDoctorId(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctor details retrieved", doctorService.getDoctorById(dto)),
                HttpStatus.OK
        );
    }

    @PutMapping(
            value = Endpoint.UPDATE_DOCTOR_DETAILS,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<StandardResponse> updateDoctorDetails(
            @PathVariable("id") String id,
            @Valid @RequestPart("doctor") UpdateDoctorDTO updateDoctorDTO,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) {
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctor details updated successfully",
                        doctorService.updateDoctorDetails(id, updateDoctorDTO, profileImage)),
                HttpStatus.OK
        );
    }

    @PostMapping(value = Endpoint.CREATE_DOCTOR)
    public ResponseEntity<StandardResponse> createDoctor(@Valid @RequestBody CreateDoctorDTO createDoctorDTO) {

        String result = doctorService.CreateDoctor(createDoctorDTO);

        if(result.equals("Successful")) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "Doctor Successfully Added to System", result),
                    HttpStatus.OK
            );
        }else{
            return new ResponseEntity<>(
                    new StandardResponse(409, "Registration Failed", result),
                    HttpStatus.OK
            );
        }

    }

    @PutMapping(value = Endpoint.APPOINTMENT_STATUS_CHANGE)
    public Object appointmentStatusChange(@RequestBody AppointmentStatusChangeRequestDTO appointmentStatusChangeRequestDTO) {
        return new ResponseEntity<>(
            appointmentClient.appointmentStatusChange(appointmentStatusChangeRequestDTO),
            HttpStatus.OK
        );
    }

    @DeleteMapping(value = Endpoint.DELETE_DOCTOR)
    public ResponseEntity<StandardResponse> deleteDoctor(@PathVariable("id") String id) {
        DeleteDoctorDTO deleteDoctorDTO = new DeleteDoctorDTO();
        deleteDoctorDTO.setDoctorId(id);
        String result = doctorService.DeleteDoctor(deleteDoctorDTO);

        if (result.equals("Deleted")) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "Doctor Successfully Deleted", result),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Deletion Failed", result),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(value = Endpoint.APPOINTMENT_GET_BY_DOCTOR_ID)
    public Object getAllAppointmentsByDoctorId(@Valid @PathVariable("doctorId") String doctorId) {
        return new ResponseEntity<>(
                appointmentClient.getAllAppointmentsByDoctorId(doctorId),
                HttpStatus.OK
        );
    }

    @GetMapping(value = Endpoint.GET_ALL_UPLOADED_REPORTS)
    public ResponseEntity<StandardResponse> getAllUploadedReports(@PathVariable("doctorId") String doctorId) {
        return new ResponseEntity<>(
                patientClient.getMedicalReportsByDoctorId(doctorId),
                HttpStatus.OK
        );
    }

    @PutMapping(value = Endpoint.TELEMEDICINE_START_SESSION)
    public ResponseEntity<Object> startTelemedicineSession(@PathVariable("sessionId") String sessionId) {
        return new ResponseEntity<>(
                telemedicineClient.startSession(sessionId),
                HttpStatus.OK
        );
    }

    @PutMapping(value = Endpoint.TELEMEDICINE_END_SESSION)
    public ResponseEntity<Object> endTelemedicineSession(@PathVariable("sessionId") String sessionId, @RequestBody(required = false) TelemedicineEndSessionRequestDTO requestDTO) {
        return new ResponseEntity<>(
                telemedicineClient.endSession(sessionId, requestDTO),
                HttpStatus.OK
        );
    }

    @PostMapping(value = Endpoint.TELEMEDICINE_CREATE_PRESCRIPTION)
    public ResponseEntity<Object> createPrescription(@Valid @RequestBody PrescriptionRequestDTO requestDTO, @RequestParam String doctorUserId) {
        return new ResponseEntity<>(
                telemedicineClient.createPrescription(requestDTO, doctorUserId),
                HttpStatus.OK
        );
    }

    @PutMapping(value = Endpoint.TELEMEDICINE_UPDATE_PRESCRIPTION)
    public ResponseEntity<Object> updatePrescription(@PathVariable("prescriptionId") String prescriptionId, @Valid @RequestBody PrescriptionUpdateRequestDTO requestDTO, @RequestParam String doctorUserId) {
        return new ResponseEntity<>(
                telemedicineClient.updatePrescription(prescriptionId, requestDTO, doctorUserId),
                HttpStatus.OK
        );
    }

    @PostMapping(value = Endpoint.TELEMEDICINE_CREATE_SESSION)
    public ResponseEntity<Object> createTelemedicineSession(@Valid @RequestBody CreateSessionRequestDTO requestDTO) {
        return new ResponseEntity<>(
                telemedicineClient.createSession(requestDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/getMaxMsUserId/internal")
    public Long getMaxMsUserId() {
        return doctorService.getMaxMsUserId();
    }
}
