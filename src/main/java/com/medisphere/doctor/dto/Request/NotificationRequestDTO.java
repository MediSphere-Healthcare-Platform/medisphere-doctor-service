package com.medisphere.doctor.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDTO {
    private String userId;
    private String userRole;
    private String message;
    private String title;
    private String channel;
    private String relatedId;
    private boolean isBroadcast;
}
