package com.ly.service;

import com.ly.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher searchTeacher(Teacher teacher);
    Teacher searchTeacherByUsername(Teacher teacher);
    Integer insertTeacher(Teacher teacher);
    Integer updatePassword(Teacher teacher);
}
