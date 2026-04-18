package com.tasktimetracker.mapper;

import com.tasktimetracker.dto.main.TaskDTO;
import com.tasktimetracker.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapperDTO {
    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO taskDTO);

}
