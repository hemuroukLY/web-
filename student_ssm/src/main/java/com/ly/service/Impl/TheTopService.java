package com.ly.service.Impl;

import com.ly.mapper.TheTopMapper;
import com.ly.pojo.ExcelGrade;
import com.ly.pojo.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TheTopService implements com.ly.service.TheTopService {
    @Autowired
    private TheTopMapper theTopMapper;

    @Override
    public List<ExcelGrade> getTop(String byName) {
        List<ExcelGrade> top = theTopMapper.getTop(byName);
        return top;
    }

    @Override
    public List<ExcelGrade> getExcelTop() {
        List<ExcelGrade> excelTop = theTopMapper.getExcelTop();
        return excelTop;
    }
}
