package com.medisphere.doctor.client;

import com.medisphere.doctor.util.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "medisphere-auth-service")
public interface AuthClient {

    @DeleteMapping("/api/v1/auth/delete/internal/{msUserId}")
    StandardResponse deleteUserByMsUserId(@PathVariable("msUserId") String msUserId);
}
