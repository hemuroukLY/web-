package com.ly.mapper;


import com.ly.pojo.ExcelGrade;
import com.ly.pojo.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheTopMapper {
    List<ExcelGrade> getTop(String byName);
    List<ExcelGrade> getExcelTop();
}
