package com.tms.controller.ui;

import com.tms.model.entity.Step;
import com.tms.model.entity.Test;
import com.tms.model.entity.user.User;
import com.tms.service.CategoryService;
import com.tms.service.StepService;
import com.tms.service.TestService;
import com.tms.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class TestController {

    TestService testService;
    StepService stepService;
    CategoryService categoryService;
    UserService userService;

    public TestController(TestService testService, StepService stepService, CategoryService categoryService, UserService userService) {
        this.testService = testService;
        this.stepService = stepService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/tests")
    public String getTests(Model model) {
        List<Test> allTests = testService.findAll();
        model.addAttribute("tests", allTests);
        return "tests-list";
    }

    @PostMapping("/addTest")
    public String addTest(@ModelAttribute @Valid Test test, Principal principal) {
        test.setUser(userService.findByEmail(principal.getName()));
        testService.save(test);
        return "redirect:/user";
    }

    @PostMapping("/save")
    public String saveTest(
            @ModelAttribute @Valid Test test,
            @RequestParam(required = false) List<String> description) {
        if (description != null) {
            for (String s : description) {
                if (stepService.findByDescription(s) != null) {
                    test.addStep(stepService.findByDescription(s));
                } else {
                    Step step = new Step(s);
                    stepService.save(step);
                    test.addStep(step);
                }
            }
        }
        testService.save(test);
        return "redirect:/myTests";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(
            @RequestParam("testId") Long id,
            Model model) {
        Test test = testService.findById(id);
        model.addAttribute("test", test);
        model.addAttribute("categories", categoryService.findAll());
        return "test-form";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("testId") Long id, HttpServletRequest request) {
        testService.deleteById(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/myTests")
    public String getCurrentUserTests(Principal principal, Model model) {
        model.addAttribute("tests", testService.getUserTests(userService.findByEmail(principal.getName()).getId()));
        Test test = new Test();
        model.addAttribute("test", test);
        model.addAttribute("categories", categoryService.findAll());
        return "user";
    }

    @GetMapping("/addNewTest")
    public String addNewTest(Model model, Principal principal) {
        Test test = new Test();
        User user = userService.findByEmail(principal.getName());
        test.setUser(user);
        model.addAttribute("test", test);
        model.addAttribute("categories", categoryService.findAll());
        return "test-form";
    }
}
