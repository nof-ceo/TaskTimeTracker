package com.tasktimetracker.mapper;

import com.tasktimetracker.entity.TimeRecord;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper
public interface TimeRecordMapper {
    @Insert("""
        INSERT INTO time_records (id, employee_id, task_id, start_time, end_time, description)
        VALUES (#{id}, #{employeeId}, #{taskId}, #{startTime}, #{endTime}, #{description})
    """)
    void insert(TimeRecord timeRecord);

    @Select("""
    SELECT tr.id, tr.employee_id, tr.start_time, tr.end_time, tr.description,
        t.id as task_id, t.name as task_name, t.description as task_description, t.status as task_status
    FROM time_records tr
    JOIN tasks t ON tr.task_id = t.id
    WHERE tr.id = #{id}
""")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "employeeId", column = "employee_id"),
        @Result(property = "task.id", column = "task_id"),
        @Result(property = "task.name", column = "task_name"),
        @Result(property = "task.description", column = "task_description"),
        @Result(property = "task.status", column = "task_status")
    })
    TimeRecord findById(UUID id);

    @Select("""
    SELECT tr.id, tr.employee_id, tr.start_time, tr.end_time, tr.description,
        t.id as task_id, t.name as task_name, t.description as task_description, t.status as task_status
    FROM time_records tr
    JOIN tasks t ON tr.task_id = t.id
    WHERE tr.task_id = #{taskId}
""")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employeeId", column = "employee_id"),
            @Result(property = "task.id", column = "task_id"),
            @Result(property = "task.name", column = "task_name"),
            @Result(property = "task.description", column = "task_description"),
            @Result(property = "task.status", column = "task_status")
    })
    List<TimeRecord> findByTaskId(UUID taskId);

    @Select("""
    SELECT *
    FROM time_records
    WHERE employee_id = #{employeeId}
    AND start_time >= #{from}
    AND end_time <= #{to}
""")
    
    List<TimeRecord> findByEmployeeIdAndPeriod(
            UUID employeeId,
            LocalDateTime from,
            LocalDateTime to
    );
}
