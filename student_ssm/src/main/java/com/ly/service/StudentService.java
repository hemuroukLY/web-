package com.ly.service;

import com.ly.pojo.Grade;
import com.ly.pojo.Student;

import java.util.List;


public interface StudentService {
    List<Student> getStudentList();
    Integer addStudent(Student student);
    Integer deleteStudent(int id);
    Integer updateStudent(Student student);

}
