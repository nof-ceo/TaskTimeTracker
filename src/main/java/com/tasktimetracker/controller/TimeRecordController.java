package com.tasktimetracker.controller;

import com.tasktimetracker.dto.main.CreationTimeRecordDTO;
import com.tasktimetracker.dto.main.TaskDTO;
import com.tasktimetracker.dto.main.TimeRecordDTO;
import com.tasktimetracker.dto.main.TimeRecordWithDurationDTO;
import com.tasktimetracker.service.TimeRecordService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/time")
@Tag(name = "Task", description = "Операции с временем затраченным на задачи")
@SecurityRequirement(name = "bearer-jwt")
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    @Autowired
    public TimeRecordController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @Operation(
            summary = "Создает запись о затраченном времени"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "успешно создана",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка создания",
                    content = @Content)
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TimeRecordDTO createTimeRecord(@Valid @RequestBody CreationTimeRecordDTO creationTimeRecordDTO) {
        return timeRecordService.createTimeRecord(creationTimeRecordDTO);
    }

    @Operation(
            summary = "Получить затраченное время сотрудника за период"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно получены данные",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TimeRecordDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка входных данных",
                    content = @Content
            )
    })
    @GetMapping("/employee/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TimeRecordWithDurationDTO> getEmployeeTimeRecords(@PathVariable UUID employeeId, @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to
    ) {
        return timeRecordService.getEmployeeTimeRecordsForPeriod(employeeId, from, to);
    }
}
