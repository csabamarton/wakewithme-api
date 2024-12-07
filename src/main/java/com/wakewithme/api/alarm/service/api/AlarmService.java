package com.wakewithme.api.alarm.service.api;

import com.wakewithme.api.alarm.web.request.AlarmDto;
import com.wakewithme.api.alarm.web.response.AlarmResponse;

import java.util.List;
import java.util.UUID;

public interface AlarmService {
    AlarmResponse saveAlarm(AlarmDto alarmDto);

    List<AlarmResponse> getAlarmsByUserId(UUID userId);

    AlarmResponse getAlarmById(UUID alarmId);

    void deleteAlarm(UUID alarmId);
}
