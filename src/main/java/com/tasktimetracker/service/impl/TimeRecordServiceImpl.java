package com.tasktimetracker.service.impl;

import com.tasktimetracker.dto.main.CreationTimeRecordDTO;
import com.tasktimetracker.dto.main.TimeRecordDTO;
import com.tasktimetracker.entity.TimeRecord;
import com.tasktimetracker.exception.TaskNotFoundException;
import com.tasktimetracker.mapper.TaskMapper;
import com.tasktimetracker.mapper.TaskMapperDTO;
import com.tasktimetracker.mapper.TimeRecordMapper;
import com.tasktimetracker.mapper.TimeRecordMapperDTO;
import com.tasktimetracker.service.TaskService;
import com.tasktimetracker.service.TimeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TimeRecordServiceImpl implements TimeRecordService {

    private final TimeRecordMapper timeRecordMapper;
    private final TaskService taskService;
    private final TaskMapperDTO taskMapperDTO;
    private final TimeRecordMapperDTO timeRecordMapperDTO;

    @Autowired
    public TimeRecordServiceImpl(TimeRecordMapper timeRecordMapper, TaskService taskService, TaskMapperDTO taskMapperDTO, TimeRecordMapperDTO timeRecordMapperDTO) {
        this.timeRecordMapper = timeRecordMapper;
        this.taskService = taskService;
        this.taskMapperDTO = taskMapperDTO;
        this.timeRecordMapperDTO = timeRecordMapperDTO;
    }

    @Override
    public TimeRecordDTO createTimeRecord(CreationTimeRecordDTO creationTimeRecordDTO) throws TaskNotFoundException {
        UUID employeeId = creationTimeRecordDTO.employeeId();
        if (employeeId == null) {
            employeeId = UUID.randomUUID();
        }
        TimeRecord timeRecord = TimeRecord.builder().id(UUID.randomUUID())
                .employeeId(employeeId)
                .startTime(creationTimeRecordDTO.startTime())
                .endTime(creationTimeRecordDTO.endTime())
                .taskId(taskMapperDTO.toEntity(taskService.findById(creationTimeRecordDTO.taskId())))
                .description(creationTimeRecordDTO.description()).build();

        return timeRecordMapperDTO.toDTO(timeRecord);
    }
}
