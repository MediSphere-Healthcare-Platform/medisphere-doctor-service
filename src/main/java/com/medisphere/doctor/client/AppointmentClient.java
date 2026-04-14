package com.medisphere.doctor.client;

import com.medisphere.doctor.dto.Request.AppointmentStatusChangeRequestDTO;
import com.medisphere.doctor.util.Endpoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "medisphere-appointment-service", path = "/api/v1")
public interface AppointmentClient {

    @PutMapping(value = Endpoint.APPOINTMENT_STATUS_CHANGE)
    Object appointmentStatusChange(@RequestBody AppointmentStatusChangeRequestDTO appointmentStatusChangeRequestDTO);
}
