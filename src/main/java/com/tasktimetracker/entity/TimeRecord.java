package com.tasktimetracker.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class TimeRecord {
    private UUID id;
    private UUID employeeId;
    private Task taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
