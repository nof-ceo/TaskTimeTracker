package com.tasktimetracker.dto.main;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record TimeRecordWithDurationDTO(

        UUID id,
        @Schema(description = "ID работника", example = "30928we-23few34-34rf34")
        UUID employeeId,

        @Schema(description = "Задача")
        UUID taskId,

        @Schema(description = "Время начала", example = "2026-04-18T10:00:00")
        LocalDateTime startTime,

        @Schema(description = "Время конца", example = "2026-04-18T12:30:00")
        LocalDateTime endTime,

        @Schema(description = "Описание")
        String description,

        @Schema(description = "Затраченное время в минутах", example = "150")
        long durationMinutes
) {}