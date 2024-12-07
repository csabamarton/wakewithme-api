package com.wakewithme.api.alarm.service;

import com.wakewithme.api.alarm.entity.Alarm;
import com.wakewithme.api.alarm.repository.AlarmRepository;
import com.wakewithme.api.alarm.service.api.AlarmService;
import com.wakewithme.api.alarm.web.request.AlarmDto;
import com.wakewithme.api.alarm.web.response.AlarmResponse;
import com.wakewithme.api.user.entity.User;
import com.wakewithme.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    @Override
    public AlarmResponse saveAlarm(AlarmDto alarmDto) {
        User user = userRepository.findById(alarmDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Alarm alarm = Alarm.builder()
                .user(user)
                .datetime(alarmDto.getDatetime())
                .recurringDays(alarmDto.getRecurringDays())
                .isRecurring(alarmDto.isRecurring())
                .isEnabled(alarmDto.isEnabled())
                .label(alarmDto.getLabel())
                .visibility(alarmDto.getVisibility())
                .timezone(alarmDto.getTimezone())
                .build();

        Alarm savedAlarm = alarmRepository.save(alarm);

        return toResponse(savedAlarm);
    }

    @Override
    public List<AlarmResponse> getAlarmsByUserId(UUID userId) {
        List<Alarm> alarms = alarmRepository.findByUserId(userId);
        return alarms.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public AlarmResponse getAlarmById(UUID alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new IllegalArgumentException("Alarm not found"));
        return toResponse(alarm);
    }

    @Override
    public void deleteAlarm(UUID alarmId) {
        if (!alarmRepository.existsById(alarmId)) {
            throw new IllegalArgumentException("Alarm not found");
        }
        alarmRepository.deleteById(alarmId);
    }

    private AlarmResponse toResponse(Alarm alarm) {
        return AlarmResponse.builder()
                .id(alarm.getId())
                .datetime(alarm.getDatetime())
                .recurringDays(alarm.getRecurringDays())
                .isRecurring(alarm.isRecurring())
                .isEnabled(alarm.isEnabled())
                .label(alarm.getLabel())
                .visibility(alarm.getVisibility())
                .timezone(alarm.getTimezone())
                .createdAt(alarm.getCreatedAt())
                .updatedAt(alarm.getUpdatedAt())
                .build();
    }
}
