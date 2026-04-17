package com.medisphere.doctor.client;

import com.medisphere.doctor.dto.Request.NotificationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "medisphere-notification-service", path = "/api/v1/notifications")
public interface NotificationClient {

    @PostMapping("/create")
    Object createNotification(@RequestBody NotificationRequestDTO notificationRequestDTO);
}
