package com.wakewithme.api.alarm.repository;

import com.wakewithme.api.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlarmRepository extends JpaRepository<Alarm, UUID> {
    List<Alarm> findByUserId(UUID userId);
}
