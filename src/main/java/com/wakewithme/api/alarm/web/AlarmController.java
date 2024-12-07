package com.wakewithme.api.alarm.web;

import com.wakewithme.api.alarm.service.api.AlarmService;
import com.wakewithme.api.alarm.web.request.AlarmDto;
import com.wakewithme.api.alarm.web.response.AlarmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @PostMapping
    public ResponseEntity<AlarmResponse> saveAlarm(@RequestBody AlarmDto alarmDto) {
        return ResponseEntity.ok(alarmService.saveAlarm(alarmDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlarmResponse>> getAlarmsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(alarmService.getAlarmsByUserId(userId));
    }

    @GetMapping("/{alarmId}")
    public ResponseEntity<AlarmResponse> getAlarmById(@PathVariable UUID alarmId) {
        return ResponseEntity.ok(alarmService.getAlarmById(alarmId));
    }

    @DeleteMapping("/{alarmId}")
    public ResponseEntity<Void> deleteAlarm(@PathVariable UUID alarmId) {
        alarmService.deleteAlarm(alarmId);
        return ResponseEntity.noContent().build();
    }
}
