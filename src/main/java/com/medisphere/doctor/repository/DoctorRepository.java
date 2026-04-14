package com.medisphere.doctor.repository;

import com.medisphere.doctor.dto.Request.CreateDoctorDTO;
import com.medisphere.doctor.dto.Request.GetByIdDoctorDTO;
import com.medisphere.doctor.entity.DoctorEntity;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {

    @Query(value = "SELECT * FROM medisphere_doctor WHERE doctor_id = :#{#doctorId.doctorId}" , nativeQuery = true)
    DoctorEntity getDoctorByDoctorId(@org.springframework.data.repository.query.Param("doctorId") GetByIdDoctorDTO doctorId);

    DoctorEntity findByDoctorId(String doctorId);

    @Query(value = "SELECT * FROM medisphere_doctor WHERE ms_user_id = :msUserId" , nativeQuery = true)
    DoctorEntity getDoctorByMsUserId(@Param("msUserId") String msUserId);

    @Query(value = "SELECT * FROM medisphere_doctor WHERE dr_nic = :drNic" , nativeQuery = true)
    DoctorEntity findDoctorByNIC(@Param("drNic") String drNic);

    @Query(value = "SELECT * FROM medisphere_doctor WHERE status = 'ACTIVE'", nativeQuery = true)
    List<DoctorEntity> getActiveDoctors();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM medisphere_doctor WHERE doctor_id = :doctorId", nativeQuery = true)
    void deleteByDoctorId(@Param("doctorId") String doctorId);
}
