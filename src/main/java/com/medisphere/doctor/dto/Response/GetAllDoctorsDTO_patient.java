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
    private String drName;
    private String specialty;
    private String drContactNo;
}
