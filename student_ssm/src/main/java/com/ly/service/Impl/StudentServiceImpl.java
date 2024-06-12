package com.ly.service.Impl;

import com.ly.mapper.GradeMapper;
import com.ly.mapper.StudentMapper;
import com.ly.pojo.Grade;
import com.ly.pojo.Student;
import com.ly.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GradeMapper gradeMapper;

//获取学生列表
    @Override
    public List<Student> getStudentList() {
        List<Student> list = studentMapper.getStudentList();
        return list;
    }

//添加学生
    @Override
    public Integer addStudent(Student student) {
        Integer i = studentMapper.addStudent(student);
        List<Student> list = studentMapper.getStudentList();
        Student newStudent = list.get(list.size() - 1);
        gradeMapper.addGrade(new Grade(newStudent.getId(),newStudent.getName(),0,0,0,0));
        return i;
    }

//删除学生
    @Override
    public Integer deleteStudent(int id) {
        Integer i = studentMapper.deleteStudent(id);
        return i;
    }

//更新学生信息
    @Override
    public Integer updateStudent(Student student) {
        Integer i = studentMapper.updateStudent(student);
        return i;
    }
//更新成绩



}
