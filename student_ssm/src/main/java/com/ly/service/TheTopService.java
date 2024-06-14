package com.ly.service;

import com.ly.pojo.Grade;

import java.util.List;

public interface TheTopService {
    List<Grade> getTop(String byName);
}
