package com.test.management.system.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "redirect:user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "redirect:myTests";
    }
}
