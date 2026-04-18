package com.tasktimetracker.controller;

import com.tasktimetracker.dto.main.CreationTaskDTO;
import com.tasktimetracker.dto.main.TaskDTO;
import com.tasktimetracker.dto.main.UpdateTaskStatusDTO;
import com.tasktimetracker.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/task")
@Tag(name = "Task", description = "Операции с задачами")
@SecurityRequirement(name = "bearer-jwt")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController (TaskService taskService){
        this.taskService = taskService;
    }

    @Operation(
            summary = "Создает задачу"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно создана",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка создания задачи",
                    content = @Content)
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@Valid @RequestBody CreationTaskDTO creationTaskDTO) {
        return taskService.createTask(creationTaskDTO);
    }

    @Operation(
            summary = "Ищет задачу по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно найдена",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "404", description = "Задача отсутствует",
                    content = @Content)
    })
    @GetMapping("/find")
    @ResponseStatus(HttpStatus.FOUND)
    public TaskDTO findById(@Valid @RequestParam(name = "id") UUID id) {
        return taskService.findById(id);
    }

    @Operation(
            summary = "Обновляет статус задачи"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Статус обновлен",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "404", description = "Задача отсутствует",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Неверный статус",
                    content = @Content)
    })
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.FOUND)
    public TaskDTO updateStatusById(@Valid @RequestBody UpdateTaskStatusDTO taskStatusDTO) {
        return taskService.updateStatusById(taskStatusDTO);
    }
}
