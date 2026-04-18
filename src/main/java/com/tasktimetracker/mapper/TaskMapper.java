package com.tasktimetracker.mapper;

import com.tasktimetracker.entity.Task;
import com.tasktimetracker.state.TaskStatus;
import org.apache.ibatis.annotations.*;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface TaskMapper {
    @Insert("""
        INSERT INTO tasks (id, name, description, status) 
        VALUES (#{id}, #{name}, #{description}, #{status})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Task task);

    @Select("""
        SELECT * FROM tasks
        WHERE id = #{id}
    """)
    Optional<Task> findById(UUID id);

    @Update("""
        UPDATE tasks
        SET status = #{status}
        WHERE id = #{id}
    """)
    int updateStatusById(UUID id, TaskStatus status);
}
