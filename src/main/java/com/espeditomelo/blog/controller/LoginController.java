package com.espeditomelo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @GetMapping("/admin")
    public String getAdminForm(){
        return "adminForm";
    }


}
