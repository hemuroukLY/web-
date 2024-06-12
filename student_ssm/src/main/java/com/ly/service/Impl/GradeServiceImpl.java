package com.ly.service.Impl;

import com.ly.mapper.GradeMapper;
import com.ly.pojo.ExcelGrade;
import com.ly.pojo.Grade;
import com.ly.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<ExcelGrade> getExcelGrade() {
        List<ExcelGrade> list=gradeMapper.getExcelGrade();
        return list;
    }

    //获取所有成绩
    @Override
    public List<Grade> getAllGradeList() {
        List<Grade> list=gradeMapper.getALlGradeList();
        return list;
    }
    //根据id查询成绩
    @Override
    public Grade searchGradeById(int id) {
        Grade grade=gradeMapper.searchGradeById(id);
        return grade;
    }
    //根据name查询成绩
    @Override
    public List<Grade> searchGradeByName(String name) {
        List<Grade> list = gradeMapper.searchGradeByName(name);
        return list;
    }

    @Override
    public Integer deleteGrade(int id) {
        Integer i = gradeMapper.deleteGrade(id);
        return i;
    }
    @Override
    public Integer updateGrade(Grade grade) {
        Grade gradeBefore = gradeMapper.searchGradeById(grade.getId());
        if(grade.getMath() == -1){
            grade.setMath(gradeBefore.getMath());
        }if(grade.getJava() == -1){
            grade.setJava(gradeBefore.getJava());
        }if(grade.getEnglish() == -1){
            grade.setEnglish(gradeBefore.getEnglish());
        }if(grade.getSports() == -1){
            grade.setSports(gradeBefore.getSports());
        }
        Integer i = gradeMapper.updateGrade(grade);
        return i;
    }


}
