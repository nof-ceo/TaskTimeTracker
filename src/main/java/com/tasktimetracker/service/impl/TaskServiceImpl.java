package com.tasktimetracker.service.impl;

import com.tasktimetracker.dto.main.CreationTaskDTO;
import com.tasktimetracker.dto.main.TaskDTO;
import com.tasktimetracker.dto.main.UpdateTaskStatusDTO;
import com.tasktimetracker.exception.IdNullException;
import com.tasktimetracker.exception.TaskCreationException;
import com.tasktimetracker.exception.TaskNotFoundException;
import com.tasktimetracker.mapper.TaskMapper;
import com.tasktimetracker.mapper.TaskMapperDTO;
import com.tasktimetracker.service.TaskService;
import com.tasktimetracker.state.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tasktimetracker.entity.Task;

import java.util.Optional;
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
    public TaskDTO findById(UUID id) throws IdNullException, TaskNotFoundException {
        if (id == null)
            throw new IdNullException("ID не должно быть пустым");

        Optional<Task> task = taskMapper.findById(id);

        if (task.isEmpty())
            throw new TaskNotFoundException("Задача с данным ID не была найдена");
        return taskMapperDTO.toDTO(task.get());
    }

    @Override
    public TaskDTO updateStatusById(UpdateTaskStatusDTO taskStatusDTO) throws TaskNotFoundException, TaskCreationException{
        if (taskStatusDTO.status().equals(TaskStatus.DONE.name())
                || taskStatusDTO.status().equals(TaskStatus.IN_PROGRESS.name())
                || taskStatusDTO.status().equals(TaskStatus.NEW.name())) {
            int updated = taskMapper.updateStatusById(taskStatusDTO.id(), taskStatusDTO.status());

            if (updated == 0) {
                throw new TaskNotFoundException("Задача не найдена");
            }

            return taskMapperDTO.toDTO(taskMapper.findById(taskStatusDTO.id()).get());
        } else
            throw new TaskCreationException("Неверный тип статуса, используйте: DONE, IN_PROGRESS, NEW");
    }
}
