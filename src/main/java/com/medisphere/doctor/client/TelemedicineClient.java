package com.medisphere.doctor.client;

import com.medisphere.doctor.dto.Telemedicine.CreateSessionRequestDTO;
import com.medisphere.doctor.dto.Telemedicine.PrescriptionRequestDTO;
import com.medisphere.doctor.dto.Telemedicine.PrescriptionUpdateRequestDTO;
import com.medisphere.doctor.dto.Telemedicine.TelemedicineEndSessionRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "medisphere-telemedicine-service", path = "/api")
public interface TelemedicineClient {

    @PostMapping("/sessions")
    Object createSession(@RequestBody CreateSessionRequestDTO requestDTO);

    @GetMapping("/sessions/{sessionId}")
    Object getSessionById(@PathVariable("sessionId") String sessionId, @RequestParam("userId") String userId, @RequestParam("role") String role);

    @GetMapping("/sessions/doctor/{doctorId}")
    Object getSessionsByDoctorId(@PathVariable("doctorId") String doctorId);

    @GetMapping("/sessions/doctor/{doctorId}/pending")
    Object getPendingSessionsByDoctorId(@PathVariable("doctorId") String doctorId);

    @PutMapping("/sessions/{sessionId}/accept")
    Object acceptSession(@PathVariable("sessionId") String sessionId, @RequestParam("doctorUserId") String doctorUserId);

    @PutMapping("/sessions/{sessionId}/reject")
    Object rejectSession(@PathVariable("sessionId") String sessionId, @RequestParam("doctorUserId") String doctorUserId);

    @PutMapping("/sessions/{sessionId}/start")
    Object startSession(@PathVariable("sessionId") String sessionId);

    @PutMapping("/sessions/{sessionId}/end")
    Object endSession(@PathVariable("sessionId") String sessionId, @RequestBody TelemedicineEndSessionRequestDTO requestDTO);

    @PutMapping("/sessions/{sessionId}/cancel")
    Object cancelSession(@PathVariable("sessionId") String sessionId);

    @PostMapping("/prescriptions")
    Object createPrescription(@RequestBody PrescriptionRequestDTO requestDTO, @RequestParam("doctorUserId") String doctorUserId);

    @PutMapping("/prescriptions/{prescriptionId}")
    Object updatePrescription(@PathVariable("prescriptionId") String prescriptionId, @RequestBody PrescriptionUpdateRequestDTO requestDTO, @RequestParam("doctorUserId") String doctorUserId);
}
