package com.tasktimetracker.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TimeRecord {
    private UUID id;
    private UUID employeeId;
    private UUID taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
