package com.tasktimetracker.mapper;

import com.tasktimetracker.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
    void findById(UUID id);
}
