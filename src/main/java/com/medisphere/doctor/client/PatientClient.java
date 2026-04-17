package com.medisphere.doctor.client;

import com.medisphere.doctor.util.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "medisphere-patient-service", path = "/patient/api/v1")
public interface PatientClient {

    @GetMapping(value = "/getMedicalReportsByDoctorId/{doctorId}")
    StandardResponse getMedicalReportsByDoctorId(@PathVariable("doctorId") String doctorId);
}
