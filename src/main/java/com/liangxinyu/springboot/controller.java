package com.liangxinyu.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class controller {
    @GetMapping("/hello")
    public String hello(){
        String java="";
        return "Hello,My World";
    }
}
