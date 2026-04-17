package com.medisphere.doctor.dto.Telemedicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequestDTO {
    private String patientId;
    private String doctorId;
    private LocalDateTime scheduledAt;
}
