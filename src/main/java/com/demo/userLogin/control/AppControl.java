package com.demo.userLogin.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppControl {
    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "register.html";
    }
}
