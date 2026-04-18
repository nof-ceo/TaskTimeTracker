package com.tasktimetracker.service.impl;

import com.tasktimetracker.dto.main.CreationTimeRecordDTO;
import com.tasktimetracker.dto.main.TimeRecordDTO;
import com.tasktimetracker.dto.main.TimeRecordWithDurationDTO;
import com.tasktimetracker.entity.TimeRecord;
import com.tasktimetracker.exception.TaskNotFoundException;
import com.tasktimetracker.exception.TimeRecordException;
import com.tasktimetracker.mapper.TaskMapper;
import com.tasktimetracker.mapper.TaskMapperDTO;
import com.tasktimetracker.mapper.TimeRecordMapper;
import com.tasktimetracker.mapper.TimeRecordMapperDTO;
import com.tasktimetracker.service.TaskService;
import com.tasktimetracker.service.TimeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
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
                .taskId(creationTimeRecordDTO.taskId())
                .description(creationTimeRecordDTO.description()).build();

        timeRecordMapper.insert(timeRecord);

        return timeRecordMapperDTO.toDTO(timeRecord);
    }

    @Override
    public List<TimeRecordWithDurationDTO> getEmployeeTimeRecordsForPeriod(UUID employeeId, LocalDateTime from, LocalDateTime to) throws IllegalArgumentException, TimeRecordException{
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("from должно быть меньше to");
        }

        List<TimeRecord> records = timeRecordMapper.findByEmployeeIdAndPeriod(employeeId, from, to);
        if (records.isEmpty()) {
            throw new TimeRecordException("Неверный id работника или за данный промежуток нет данных");
        }

        return records.stream()
                .map(record -> {
                    long duration = 0;
                    if (record.getStartTime() != null && record.getEndTime() != null) {
                        duration = Duration.between(record.getStartTime(), record.getEndTime()).toMinutes();
                    }

                    return new TimeRecordWithDurationDTO(
                            record.getId(),
                            record.getEmployeeId(),
                            record.getTaskId(),
                            record.getStartTime(),
                            record.getEndTime(),
                            record.getDescription(),
                            duration
                    );
                })
                .toList();
    }
}
