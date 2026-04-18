package com.tasktimetracker.service;

import com.tasktimetracker.dto.main.CreationTimeRecordDTO;
import com.tasktimetracker.dto.main.TimeRecordDTO;
import com.tasktimetracker.dto.main.TimeRecordWithDurationDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TimeRecordService {
    TimeRecordDTO createTimeRecord(CreationTimeRecordDTO creationTimeRecordDTO);
    List<TimeRecordWithDurationDTO> getEmployeeTimeRecordsForPeriod(UUID employeeId, LocalDateTime from, LocalDateTime to);
}
