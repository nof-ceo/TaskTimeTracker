package com.tasktimetracker.mapper;

import com.tasktimetracker.entity.TimeRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

public interface TimeRecordMapper {
    @Insert("""
        INSERT INTO time_records (employee_id, task_id, start_time, end_time, description)
        VALUES (#{employeeId}, #{task.taskId}, #{startTime}, #{endTime}, #{description})
    """)
    void insert(TimeRecord timeRecord);

    @Select("""
    SELECT tr.id, tr.employee_id, tr.start_time, tr.end_time, tr.description,
        t.id as task_id, t.name as task_name, t.description as task_description, t.status as task_status
    FROM time_record tr
    JOIN task t ON tr.task_id = t.id
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
    FROM time_record tr
    JOIN task t ON tr.task_id = t.id
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
}
