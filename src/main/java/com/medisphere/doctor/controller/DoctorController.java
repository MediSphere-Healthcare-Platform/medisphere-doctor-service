package com.medisphere.doctor.controller;

import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.dto.Response.GetAllDoctorsDTO_patient;
import com.medisphere.doctor.entity.DoctorEntity;
import com.medisphere.doctor.service.DoctorService;
import com.medisphere.doctor.util.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping(value = Endpoint.GET_ALL_DOCTORS_FOR_PATIENT)
    public List<GetAllDoctorsDTO_patient> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping(value = Endpoint.GET_DOCTOR_BY_ID)
    public GetByIdDoctorDTO getDoctorById(@PathVariable("id") String id){
        GetByIdDoctorDTO dto = new GetByIdDoctorDTO();
        dto.setDoctorId(id);
        return doctorService.getDoctorById(dto);
    }

}
