package com.ly.mapper;

import com.ly.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    Teacher searchTeacher(Teacher  teacher);
    Integer insertTeacher(Teacher teacher);
    Integer updatePassword(Teacher teacher);
    Teacher searchTeacherByUsername(String username);
}
