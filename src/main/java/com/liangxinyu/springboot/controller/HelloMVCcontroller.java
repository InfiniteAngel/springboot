package com.liangxinyu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/test")
public class HelloMVCcontroller {
    @RequestMapping ("/hello")
    public String hello(Model model){
        model.addAttribute("mav","Helo,Spring");
        return "example/text";
    }
}
