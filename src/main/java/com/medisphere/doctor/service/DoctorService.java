package com.medisphere.doctor.service;

import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.dto.Response.GetAllDoctorsDTO_patient;
import com.medisphere.doctor.entity.DoctorEntity;
import com.medisphere.doctor.repository.DoctorRepository;
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

    public List<GetAllDoctorsDTO_patient>  getAllDoctors(){
        List<DoctorEntity> getAllDoctors = doctorRepository.findAll();
        return modelMapper.map(getAllDoctors, new TypeToken<List<GetAllDoctorsDTO_patient>>(){}.getType());
    }

    public GetByIdDoctorDTO getDoctorById(GetByIdDoctorDTO id){
        DoctorEntity doctors = doctorRepository.getDoctorByDoctorId(id);
        return modelMapper.map(doctors, GetByIdDoctorDTO.class);
    }


}
