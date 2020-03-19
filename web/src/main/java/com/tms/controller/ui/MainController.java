package com.tms.controller.ui;

import org.springframework.stereotype.Controller;
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
    public String userIndex(Principal principal) {
        if (principal.getName().equals("manager"))
            return "redirect:admin";
        else
            return "redirect:myTests";
    }
}
