package com.ly.mapper;


import com.ly.pojo.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheTopMapper {
    List<Grade> getTop(String name);
}
