package com.ly.mapper;

import com.ly.pojo.Grade;
import com.ly.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    List<Student> getStudentList();


    Integer addStudent(Student student);



    Integer deleteStudent(int id);



    Integer updateStudent(Student student);




}
