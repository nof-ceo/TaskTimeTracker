package com.tasktimetracker.dto.main;

import com.tasktimetracker.state.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "DTO описывающее задачу")
public record CreationTaskDTO (
    @Schema(description = "Имя задачи", example = "Рефакторинг")
    String name,

    @Schema(description = "Описание задачи", example = "Провести анализ блоков x, y, z, написать отчет и скинуть его до n часов")
    String description,

    @Schema(description = "Статус задачи", example = "IN_PROGRESS")
    String status
) {}
