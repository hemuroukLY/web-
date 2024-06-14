package com.ly.controller;

import com.ly.pojo.Grade;
import com.ly.service.TheTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600000)
@RestController
public class TheTopController {
    @Autowired
    private TheTopService theTopService;

    @GetMapping("/searchTop/{byName}")
    public List<Grade> getTop(@PathVariable String byName){
        List<Grade> top = theTopService.getTop(byName);
        return top;
    }
}
