package com.ly.service;

import com.ly.pojo.ExcelGrade;
import com.ly.pojo.Grade;

import java.util.List;

public interface GradeService {
    List<ExcelGrade> getExcelGrade();
    List<Grade> getAllGradeList();
    Grade searchGradeById(int id);
    List<Grade> searchGradeByName(String name);
    Integer deleteGrade(int id);
    Integer updateGrade(Grade grade);

}
