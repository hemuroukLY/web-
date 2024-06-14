package com.ly.service;

import com.ly.pojo.ExcelGrade;


import java.util.List;

public interface TheTopService {
    List<ExcelGrade> getTop(String byName);
    List<ExcelGrade> getExcelTop();
}
