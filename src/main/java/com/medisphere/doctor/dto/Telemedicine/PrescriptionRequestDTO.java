package com.medisphere.doctor.dto.Telemedicine;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class PrescriptionRequestDTO {

    @NotBlank(message = "Session ID is required")
    private String sessionId;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @NotEmpty(message = "At least one medication is required")
    @Valid
    private List<MedicationItemDTO> medications;

    private String instructions;
}
