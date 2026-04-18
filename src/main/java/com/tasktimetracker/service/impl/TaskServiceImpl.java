package com.tasktimetracker.service.impl;

import com.tasktimetracker.dto.main.CreationTaskDTO;
import com.tasktimetracker.dto.main.TaskDTO;
import com.tasktimetracker.exception.TaskCreationException;
import com.tasktimetracker.mapper.TaskMapper;
import com.tasktimetracker.mapper.TaskMapperDTO;
import com.tasktimetracker.service.TaskService;
import com.tasktimetracker.state.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tasktimetracker.entity.Task;

import java.util.UUID;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private TaskMapperDTO taskMapperDTO;

    @Autowired
    public TaskServiceImpl(TaskMapper taskMapper, TaskMapperDTO taskMapperDTO) {
        this.taskMapper = taskMapper;
        this.taskMapperDTO = taskMapperDTO;
    }

    @Override
    public TaskDTO createTask(CreationTaskDTO taskDTO) throws TaskCreationException {
        if (taskDTO.name().isEmpty())
            throw new TaskCreationException("Имя задачи не должно быть пустым");

        if (taskDTO.status().equals(TaskStatus.DONE.name())
             || taskDTO.status().equals(TaskStatus.IN_PROGRESS.name())
                || taskDTO.status().equals(TaskStatus.NEW.name())) {


            Task task = Task.builder().id(UUID.randomUUID()).
                    name(taskDTO.name()).
                    description(taskDTO.description()).
                    status(TaskStatus.valueOf(taskDTO.status()))
                    .build();

            taskMapper.insert(task);

            return taskMapperDTO.toDTO(task);
        }
        else
            throw new TaskCreationException("Неверный тип статуса, используйте: DONE, IN_PROGRESS, NEW");
    }

    @Override
    public TaskDTO findById(UUID id) {
        return null;
    }

    @Override
    public TaskDTO updateStatusById(UUID id) {
        return null;
    }
}
