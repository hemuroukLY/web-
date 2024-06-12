package com.ly.mapper;

import com.ly.pojo.ExcelGrade;
import com.ly.pojo.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeMapper {
    List<Grade> getALlGradeList();

    Grade searchGradeById(int id);

    List<Grade> searchGradeByName(String name);

    Integer addGrade(Grade grade);

    Integer deleteGrade(int id);

    Integer updateGrade(Grade grade);

    List<ExcelGrade> getExcelGrade();
}
