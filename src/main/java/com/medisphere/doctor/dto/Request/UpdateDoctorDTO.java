package com.medisphere.doctor.dto.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDoctorDTO {

    @NotNull(message = "Doctor Name can't be Null")
    @NotEmpty(message = "Doctor Name can't be Empty")
    private String drName;

    @NotNull(message = "Doctor Contact Number can't be Null")
    @NotEmpty(message = "Doctor Contact Number can't be Empty")
    private String drContactNo;

    @NotNull(message = "Doctor Status can't be Null")
    @NotEmpty(message = "Doctor Status can't be Empty")
    private String status;
}
