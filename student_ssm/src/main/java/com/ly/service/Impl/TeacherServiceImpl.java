package com.ly.service.Impl;

import com.ly.mapper.TeacherMapper;
import com.ly.pojo.Teacher;
import com.ly.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Teacher searchTeacher(Teacher teacher) {
        Teacher teacher1 = teacherMapper.searchTeacher(teacher);
        return teacher1;
    }

    @Override
    public Teacher searchTeacherByUsername(Teacher teacher) {
        String username=teacher.getUsername();
        Teacher teacher1=teacherMapper.searchTeacherByUsername(username);
        return teacher1;
    }

    @Override
    public Integer insertTeacher(Teacher teacher) {
        Integer i = teacherMapper.insertTeacher(teacher);
        return i;
    }

    @Override
    public Integer updatePassword(Teacher teacher) {
        Integer i = teacherMapper.updatePassword(teacher);
        return i;
    }
}
