package com.tasktimetracker.entity;

import com.tasktimetracker.state.TaskStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Task {
    private UUID id;
    private String name;
    private String description;
    private TaskStatus status;
}
