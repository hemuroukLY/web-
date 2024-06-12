package com.ly.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.pojo.ExcelGrade;
import com.ly.pojo.Grade;
import com.ly.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600000)
@RestController
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/searchGradeAll/{page}")
    public PageInfo<Grade> getAllGradeList(@PathVariable int page){
        PageHelper.startPage(page,10);
        List<Grade> list=gradeService.getAllGradeList();
        PageInfo<Grade> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @GetMapping("/searchGradeById/{id}")
    public Grade searchGradeById(@PathVariable int  id){
        Grade grade = gradeService.searchGradeById(id);
        return grade;
    }

    @GetMapping("/searchGradeByName/{name}")
    public List<Grade> searchGradeByName(@PathVariable String name){
        List<Grade> list = gradeService.searchGradeByName(name);
        return list;
    }

    @PostMapping("/deleteGrade")
    public Integer deleteGrade(Integer id){
        Integer i = gradeService.deleteGrade(id);
        return i;
    }

    @PostMapping("/updateGrade")
    public Integer updateGrade(@RequestBody Grade grade){
        Integer i = gradeService.updateGrade(grade);
        return i;
    }

    @PostMapping("/getExcelGrade")
    public List<ExcelGrade> getExcelGrade(){
        List<ExcelGrade> list = gradeService.getExcelGrade();
        return list;
    }
}
