package com.medisphere.doctor.dto.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetByIdDoctorDTO {

    @NotNull(message = "Doctor ID can't be Null")
    @NotEmpty(message = "Doctor ID can't be Empty")
    private String doctorId;
    private String drName;
    private String specialty;
    private String drContactNo;
    private String drLicence;
    private String drNic;
    private String status;
}
