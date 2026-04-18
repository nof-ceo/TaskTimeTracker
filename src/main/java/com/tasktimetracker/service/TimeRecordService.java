package com.tasktimetracker.service;

import com.tasktimetracker.dto.main.CreationTimeRecordDTO;
import com.tasktimetracker.dto.main.TimeRecordDTO;

public interface TimeRecordService {
    TimeRecordDTO createTimeRecord(CreationTimeRecordDTO creationTimeRecordDTO);
}
