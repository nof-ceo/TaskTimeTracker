package com.tasktimetracker.dto.main;

import com.tasktimetracker.state.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "DTO описывающее задачу")
public record TaskDTO(
        @Schema(description = "id задачи", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,

        @Schema(description = "Имя задачи", example = "Рефакторинг")
        String name,

        @Schema(description = "Описание задачи", example = "Провести анализ блоков x, y, z, написать отчет и скинуть его до n часов")
        String description,

        @Schema(description = "Статус задачи", example = "IN_PROGRESS")
        TaskStatus status

        ) {

}
