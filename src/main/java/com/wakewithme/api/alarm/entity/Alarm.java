package com.wakewithme.api.alarm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alarms")
@Data
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime alarmTime;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
