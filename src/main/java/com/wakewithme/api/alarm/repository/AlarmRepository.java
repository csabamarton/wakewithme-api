package com.wakewithme.api.alarm.repository;

import com.wakewithme.api.alarm.entity.Alarm;
import com.wakewithme.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlarmRepository extends JpaRepository<Alarm, UUID> {
    // User repository methods will go here
}
