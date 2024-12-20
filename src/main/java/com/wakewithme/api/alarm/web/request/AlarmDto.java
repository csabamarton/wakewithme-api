package com.wakewithme.api.alarm.web.request;

import com.wakewithme.api.alarm.entity.Alarm;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AlarmDto {
    private UUID userId;
    private LocalDateTime datetime; // First occurrence of the alarm
    private String recurringDays;
    private boolean isRecurring;
    private boolean isEnabled;
    private String label;
    private Alarm.Visibility visibility;
    private String timezone;
}
