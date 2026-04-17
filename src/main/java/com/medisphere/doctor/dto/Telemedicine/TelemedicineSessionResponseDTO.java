package com.medisphere.doctor.dto.Telemedicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemedicineSessionResponseDTO {
    private String sessionId;
    private String patientId;
    private String doctorId;
    private String roomName;
    private String roomUrl;
    private String status;
    private LocalDateTime scheduledAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Integer durationMinutes;
    private String notes;
    private String requestReason;
    private String jitsiToken;
}
