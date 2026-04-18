package com.tasktimetracker;

import com.tasktimetracker.dto.main.CreationTaskDTO;
import com.tasktimetracker.dto.main.TaskDTO;
import com.tasktimetracker.dto.main.UpdateTaskStatusDTO;
import com.tasktimetracker.entity.Task;
import com.tasktimetracker.exception.TaskCreationException;
import com.tasktimetracker.exception.TaskNotFoundException;
import com.tasktimetracker.mapper.TaskMapper;
import com.tasktimetracker.mapper.TaskMapperDTO;
import com.tasktimetracker.service.impl.TaskServiceImpl;
import com.tasktimetracker.state.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private TaskMapperDTO taskMapperDTO;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    @DisplayName("Успешное создание задачи с правильным статусом")
    void createTask_Success() {
        CreationTaskDTO creationDTO = new CreationTaskDTO("Test Task", "Description", "NEW");
        TaskDTO expectedDto = new TaskDTO(UUID.randomUUID(), "Test Task", "Description", TaskStatus.NEW);

        when(taskMapperDTO.toDTO(any(Task.class))).thenReturn(expectedDto);

        TaskDTO result = taskService.createTask(creationDTO);

        assertNotNull(result);
        assertEquals("Test Task", result.name());
        verify(taskMapper, times(1)).insert(any(Task.class));
    }

    @Test
    @DisplayName("Ошибка создания задачи при пустом имени")
    void createTask_ThrowsException_WhenNameIsEmpty() {
        CreationTaskDTO creationDTO = new CreationTaskDTO("", "Description", "NEW");

        assertThrows(TaskCreationException.class, () -> taskService.createTask(creationDTO));
        verify(taskMapper, never()).insert(any(Task.class));
    }

    @Test
    @DisplayName("Ошибка при поиске по несуществующему ID")
    void findById_ThrowsException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(taskMapper.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.findById(id));
    }

    @Test
    @DisplayName("Успешное обновление статуса задачи")
    void updateStatusById_Success() {
        UUID id = UUID.randomUUID();
        UpdateTaskStatusDTO updateDTO = new UpdateTaskStatusDTO(id, TaskStatus.DONE);

        Task task = new Task(id, "Name", "Desc", TaskStatus.DONE);
        TaskDTO expectedDto = new TaskDTO(id, "Name", "Desc", TaskStatus.DONE);

        when(taskMapper.updateStatusById(eq(id), eq(TaskStatus.DONE))).thenReturn(1);
        when(taskMapper.findById(id)).thenReturn(Optional.of(task));
        when(taskMapperDTO.toDTO(task)).thenReturn(expectedDto);

        TaskDTO result = taskService.updateStatusById(updateDTO);

        assertNotNull(result);
        assertEquals(TaskStatus.DONE, result.status());
        verify(taskMapper).updateStatusById(id, TaskStatus.DONE);
    }
}