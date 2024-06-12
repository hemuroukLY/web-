package com.ly.controller;


import com.ly.mapper.TeacherMapper;
import com.ly.pojo.Teacher;
import com.ly.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600000)
@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/searchTeacher")
    public Teacher searchTeacher(@RequestBody Teacher teacher) {
        Teacher teacher1 = teacherService.searchTeacherByUsername(teacher);
        return teacher1;
    }

    @PostMapping("/updatePassword")
    public Integer updatePassword(@RequestBody Teacher teacher) {
         Integer i = teacherService.updatePassword(teacher);
        return i;
    }
}
