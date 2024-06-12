package com.ly.controller;

import com.ly.pojo.Teacher;
import com.ly.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(maxAge = 3600000)
@RestController
public class RegisterController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/register")
    public Integer register(@RequestBody Teacher teacher){
       Teacher teacher1 = teacherService.searchTeacher(teacher);
        if(teacher1==null){
            Integer i= teacherService.insertTeacher(teacher);
            return 1;
        }else {
            return 0;
        }

    }
}
