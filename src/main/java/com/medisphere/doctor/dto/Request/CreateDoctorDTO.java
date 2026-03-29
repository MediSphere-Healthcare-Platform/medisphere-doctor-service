package com.medisphere.doctor.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDoctorDTO {

    @NotNull(message = "Fields can't be null")
    @NotEmpty(message = "Fields can't be empty")
    private String drName;
    private String specialty;
    private String drLicence;
    private String drContactNo;
    private String drNic;
}
