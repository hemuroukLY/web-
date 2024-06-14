package com.ly.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.pojo.ExcelGrade;
import com.ly.pojo.Grade;
import com.ly.service.TheTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600000)
@RestController
public class TheTopController {
    @Autowired
    private TheTopService theTopService;

    @GetMapping("/searchTop/{byName}/{page}")
    public PageInfo<ExcelGrade> getTop(@PathVariable String byName , @PathVariable int page){
        PageHelper.startPage(page,10);
        List<ExcelGrade> top = theTopService.getTop(byName);
        PageInfo<ExcelGrade> excelGradePageInfo = new PageInfo<>(top);
        return excelGradePageInfo;
    }

    @PostMapping("/searchExcelTop")
    public List<ExcelGrade> getExcelGrade(){
        List<ExcelGrade> excelTop = theTopService.getExcelTop();
        return excelTop;
    }


}
