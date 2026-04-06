package com.medisphere.doctor.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllDoctorsDTO_patient {
    private String firstName;
    private String lastName;
    private String specialty;
    private String profilePic;
    private String drContactNo;
    private String status;
}
