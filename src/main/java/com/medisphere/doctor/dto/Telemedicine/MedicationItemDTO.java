package com.medisphere.doctor.dto.Telemedicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationItemDTO {
    private String name;
    private String dosage;
    private String frequency;
    private String duration;
}
