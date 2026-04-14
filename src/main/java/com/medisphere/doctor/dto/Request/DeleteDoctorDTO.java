package com.medisphere.doctor.dto.Request;

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
public class DeleteDoctorDTO {

    @NotNull(message = "Doctor ID Can't be Null")
    @NotEmpty(message = "Doctor ID can't be empty")
    private String doctorId;
}
