//package com.test.management.system.ui;
//
//import com.test.management.system.entity.Step;
//import com.test.management.system.entity.Test;
//import com.test.management.system.service.StepService;
//import com.test.management.system.service.TestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequestMapping(path = "/")
//public class TestUiController {
//
//    @Autowired
//    TestService testService;
//
//    @Autowired
//    StepService stepService;
//
//    @GetMapping("/tests")
//    public String getTests(Model model) {
//        List<Test> allTests = testService.getTests();
//        model.addAttribute("tests", allTests);
//        Test test = new Test();
//        model.addAttribute("test", test);
//        return "tests-list";
//    }
//
//    @PostMapping("/save")
//    public String saveTest(
//            @ModelAttribute @Valid Test test,
//            BindingResult bindingResult,
//            @RequestParam(required = false) String addedStep) {
//        if (addedStep != null) {
//            Step step = new Step(addedStep);
//            stepService.saveStep(step);
//            test.saveStep(step);
//
//        }
//        testService.saveTest(test);
//        return "redirect:/tests";
//    }
//
//    @PostMapping("/showFormForUpdate")
//    public String showFormForUpdate(@RequestParam("testId") int id, Model model) {
//        Test test = testService.getTest(id);
//        model.addAttribute("test", test);
//        List<Step> allSteps = stepService.getSteps();
//        model.addAttribute("allSteps", allSteps);
//        return "test-form";
//    }
//
//
//    @PostMapping("/delete")
//    public String delete(@RequestParam("testId") int id) {
//        testService.deleteTest(id);
//        return "redirect:/tests";
//    }
//
//    @PostMapping("/addStepToTest")
//    public String addStepToTest(@RequestParam("stepId") int stepId, @RequestParam("testId") int testId, Model model) {
//        Test test = testService.getTest(testId);
//        Step step = stepService.getStep(stepId);
//        test.saveStep(step);
//        testService.saveTest(test);
//
//        model.addAttribute("test", test);
//        List<Step> allSteps = stepService.getSteps();
//        model.addAttribute("allSteps", allSteps);
//        return "test-form";
//    }
//}
