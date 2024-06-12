package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect(){
        return "forward:/index.html";
    }
}