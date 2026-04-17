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

    @NotNull(message = "Doctor first Name can't be Null")
    @NotEmpty(message = "Doctor first Name can't be Empty")
    private String firstName;

    @NotNull(message = "Doctor last Name can't be Null")
    @NotEmpty(message = "Doctor last Name can't be Empty")
    private String lastName;

    private String profilePic;

    @NotNull(message = "Doctor Contact Number can't be Null")
    @NotEmpty(message = "Doctor Contact Number can't be Empty")
    private String drContactNo;

    @NotNull(message = "Doctor Status can't be Null")
    @NotEmpty(message = "Doctor Status can't be Empty")
    private String status;
}
