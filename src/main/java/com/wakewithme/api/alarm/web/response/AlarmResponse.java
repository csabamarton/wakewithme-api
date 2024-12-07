package com.wakewithme.api.alarm.web.response;

import com.wakewithme.api.alarm.entity.Alarm;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AlarmResponse {
    private UUID id;
    private LocalDateTime datetime;
    private String recurringDays;
    private boolean isRecurring;
    private boolean isEnabled;
    private String label;
    private Alarm.Visibility visibility;
    private String timezone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
