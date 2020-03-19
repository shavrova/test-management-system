package com.test.management.system.controller.ui;

import com.test.management.system.entity.user.User;
import com.test.management.system.service.TestService;
import com.test.management.system.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class AdminController {

    UserService userService;
    TestService testService;

    public AdminController(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

    @GetMapping("/admin")
    public String showAllCategories(Model model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        model.addAttribute("tests", testService.getDeletedUserTests());
        return "admin";
    }

    @GetMapping("/userTests/{userId}")
    public String getAttr(@PathVariable(value = "userId") Long userId, Model model) {
        model.addAttribute("tests", testService.getUserTests(userId));
        return "user-tests-list";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long userId) {
        testService.getUserTests(userId).forEach(s -> s.setUser(null));
        userService.deleteById(userId);
        return "redirect:/admin";
    }
}
