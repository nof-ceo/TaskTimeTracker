package com.tasktimetracker.dto.main;

import com.tasktimetracker.state.TaskStatus;

import java.util.UUID;

public record UpdateTaskStatusDTO (
        UUID id,
        TaskStatus status
) {

}
