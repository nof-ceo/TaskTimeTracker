package com.tasktimetracker.dto.main;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record TimeRecordDTO (

    UUID id,
    @Schema(description = "ID работника", example = "30928we-23few34-34rf34")
    UUID employeeId,

    @Schema(description = "ID задачи", example = "30928we-23few34-34rf34")
    UUID taskId,

    @Schema(description = "Время начала", example = "")
    LocalDateTime startTime,

    @Schema(description = "Время конца", example = "")
    LocalDateTime endTime,

    @Schema(description = "Описание", example = "")
    String description
) {}