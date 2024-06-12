package com.ly.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExcelGrade {
    private int id;
    private String name;
    private int math;
    private int java;
    private int english;
    private int sports;
    private int total;
}