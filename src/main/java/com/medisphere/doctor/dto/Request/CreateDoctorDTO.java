package com.medisphere.doctor.dto.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDoctorDTO {

    @NotNull(message = "First name Can't be Null")
    @NotEmpty(message = "Last name can't be empty")
    private String firstName;

    @NotNull(message = "Last name Can't be Null")
    @NotEmpty(message = "Last name can't be empty")
    private String lastName;

    @NotNull(message = "Doctor ID Can't be Null")
    @NotEmpty(message = "Doctor ID can't be empty")
    private String doctorId;

    @NotNull(message = "mUserId Can't be Null")
    @NotEmpty(message = "mUserId can't be empty")
    private String msUserId;

    @NotNull(message = "specialty Can't be Null")
    @NotEmpty(message = "specialty can't be empty")
    private String specialty;

    @NotNull(message = "Doctor Licence Can't be Null")
    @NotEmpty(message = "Doctor Licence can't be empty")
    private String drLicence;

    @NotNull(message = "Doctor Contact Number Can't be Null")
    @NotEmpty(message = "Doctor Contact Number can't be empty")
    private String drContactNo;

    @NotNull(message = "Doctor NIC Can't be Null")
    @NotEmpty(message = "Doctor NIC can't be empty")
    private String drNic;

    @NotNull(message = "status Can't be Null")
    @NotEmpty(message = "status can't be empty")
    private String status;

    @NotNull(message = "Profile picture Can't be Null")
    @NotEmpty(message = "Profile picture can't be empty")
    private String profilePic;

    private Instant createDate;

    private Instant modifiedDate;
}
