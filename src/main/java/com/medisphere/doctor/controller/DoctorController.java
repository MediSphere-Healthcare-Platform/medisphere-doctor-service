package com.medisphere.doctor.controller;

import com.medisphere.doctor.client.AppointmentClient;
import com.medisphere.doctor.dto.Request.*;
import com.medisphere.doctor.dto.Response.GetAllDoctorsDTO_patient;
import com.medisphere.doctor.service.DoctorService;
import com.medisphere.doctor.util.Endpoint;
import com.medisphere.doctor.util.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "doctor/api/v1")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentClient appointmentClient;

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

    @PutMapping(value = Endpoint.UPDATE_DOCTOR_DETAILS)
    public ResponseEntity<StandardResponse> updateDoctorDetails(@PathVariable("id") String id, @Valid @RequestBody UpdateDoctorDTO updateDoctorDTO) {
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctor details updated successfully", doctorService.updateDoctorDetails(id, updateDoctorDTO)),
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

}
