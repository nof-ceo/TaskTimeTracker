package com.tasktimetracker.service;

import com.tasktimetracker.dto.main.CreationTaskDTO;
import com.tasktimetracker.dto.main.TaskDTO;

import java.util.UUID;

public interface TaskService {
    TaskDTO createTask(CreationTaskDTO taskDTO);
    TaskDTO findById(UUID id);
    TaskDTO updateStatusById(UUID id);
}
