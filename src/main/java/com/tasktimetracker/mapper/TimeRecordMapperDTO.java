package com.tasktimetracker.mapper;

import com.tasktimetracker.dto.main.TimeRecordDTO;
import com.tasktimetracker.entity.TimeRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeRecordMapperDTO {
    TimeRecordDTO toDTO(TimeRecord timeRecord);
    TimeRecord toEntity(TimeRecordDTO timeRecordDTO);

}
