package com.medisphere.doctor.repository;

import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {

    @Query(value = "SELECT * FROM doctor_table WHERE doctor_id = :#{#doctorId.doctorId}" , nativeQuery = true)
    DoctorEntity getDoctorByDoctorId(@org.springframework.data.repository.query.Param("doctorId") GetByIdDoctorDTO doctorId);
}
