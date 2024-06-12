package com.ly.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.pojo.Grade;
import com.ly.pojo.Student;
import com.ly.pojo.Teacher;
import com.ly.service.GradeService;
import com.ly.service.StudentService;
import com.ly.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin(maxAge = 3600000)
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/searchStudent/{page}")
    public PageInfo<Student> getStudentList(@PathVariable int page){
        PageHelper.startPage(page,10);
        List<Student> list=studentService.getStudentList();
        PageInfo<Student> pageInfo=new PageInfo<>(list);
        return  pageInfo;
    }

    @PostMapping("/addStudent")
    public Integer addStudent(@RequestBody Student student){
        Integer i = studentService.addStudent(student);
        return i;
    }


    @GetMapping("/deleteStudent/{id}")
    public Integer deleteStudent(@PathVariable Integer id){
        Integer i = studentService.deleteStudent(id);
        return i;
    }


    @PostMapping("/updateStudent")
    public Integer updateStudent(@RequestBody Student student){
        Integer i = studentService.updateStudent(student);
        return i;
    }




}
