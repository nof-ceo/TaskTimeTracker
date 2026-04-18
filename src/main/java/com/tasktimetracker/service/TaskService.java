package com.tasktimetracker.service;

import com.tasktimetracker.dto.main.CreationTaskDTO;
import com.tasktimetracker.dto.main.TaskDTO;
import com.tasktimetracker.dto.main.UpdateTaskStatusDTO;

import java.util.UUID;

public interface TaskService {
    TaskDTO createTask(CreationTaskDTO taskDTO);
    TaskDTO findById(UUID id);
    TaskDTO updateStatusById(UpdateTaskStatusDTO taskStatusDTO);
}
