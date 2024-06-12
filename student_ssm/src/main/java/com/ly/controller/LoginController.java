package com.ly.controller;

import com.ly.pojo.Teacher;
import com.ly.service.TeacherService;
import com.ly.utils.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(maxAge = 3600000)
@RestController
public class  LoginController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/login")
    public String login(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherService.searchTeacher(teacher);
        if(teacher1 == null ){
            return "登录失败";
        }
        else {
            Map<String,Object> map = new HashMap<>();
            map.put("username",teacher1.getUsername());
            map.put("password",teacher1.getPassword());
            String jwt = jwtUtils.getJwt(map);
            return jwt;
        }
    }
}
