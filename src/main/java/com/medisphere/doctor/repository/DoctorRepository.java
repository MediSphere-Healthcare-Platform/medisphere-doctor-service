package com.medisphere.doctor.repository;

import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {

    @Query(value = "SELECT * FROM medisphere_doctor WHERE doctor_id = :#{#doctorId.doctorId}" , nativeQuery = true)
    DoctorEntity getDoctorByDoctorId(@org.springframework.data.repository.query.Param("doctorId") GetByIdDoctorDTO doctorId);

    DoctorEntity findByDoctorId(String doctorId);

    @Query(value = "SELECT * FROM medisphere_doctor WHERE status = 'ACTIVE'", nativeQuery = true)
    List<DoctorEntity> getActiveDoctors();
}
