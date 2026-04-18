package com.tasktimetracker.entity;

import com.tasktimetracker.state.TaskStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task {
    private UUID id;
    private String name;
    private String description;
    private TaskStatus status;
}
