package com.tasktimetracker.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class TimeRecord {
    private UUID id;
    private UUID employeeId;
    private Task taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
