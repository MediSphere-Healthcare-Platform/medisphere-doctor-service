package com.medisphere.doctor.controller;

import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.dto.Request.UpdateDoctorDTO;
import com.medisphere.doctor.dto.Response.GetAllDoctorsDTO_patient;
import com.medisphere.doctor.service.DoctorService;
import com.medisphere.doctor.util.Endpoint;
import com.medisphere.doctor.util.StandardResponse;
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

    @GetMapping(value = Endpoint.GET_ALL_DOCTORS_FOR_PATIENT)
    public ResponseEntity<StandardResponse> getAllDoctors() {
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctors retrieved successfully", doctorService.getAllDoctors()),
                HttpStatus.OK
        );
    }

    @GetMapping(value = Endpoint.GET_DOCTOR_BY_ID)
    public ResponseEntity<StandardResponse> getDoctorById(@PathVariable("id") String id) {
        GetByIdDoctorDTO dto = new GetByIdDoctorDTO();
        dto.setDoctorId(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctor details retrieved", doctorService.getDoctorById(dto)),
                HttpStatus.OK
        );
    }

    @PutMapping(value = Endpoint.UPDATE_DOCTOR_DETAILS)
    public ResponseEntity<StandardResponse> updateDoctorDetails(@PathVariable("id") String id, @RequestBody UpdateDoctorDTO updateDoctorDTO) {
        return new ResponseEntity<>(
                new StandardResponse(200, "Doctor details updated successfully", doctorService.updateDoctorDetails(id, updateDoctorDTO)),
                HttpStatus.OK
        );
    }

}
