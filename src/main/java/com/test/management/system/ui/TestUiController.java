package com.test.management.system.ui;

import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.entity.TestStep;
import com.test.management.system.service.StepService;
import com.test.management.system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "/")
public class TestUiController {

    @Autowired
    TestService testService;

    @Autowired
    StepService stepService;

    @GetMapping("/tests")
    public String getTests(Model model) {
        Set<Test> allTests = testService.findAll();
        model.addAttribute("tests", allTests);
        Test test = new Test();
        model.addAttribute("test", test);
        List<TestStep> ts = test.getSteps();
        //.forEach(s->s.getStep().getStepDescription());
        return "tests-list";
    }

    @PostMapping("/save")
    public String saveTest(
            @ModelAttribute @Valid Test test,
            BindingResult bindingResult,
            @RequestParam(required = false) Set<String> newStepDescription) {
        if (!newStepDescription.isEmpty()) {
            newStepDescription.forEach(s -> {
                Step step = new Step(s);
                stepService.save(step);
                test.addStep(step);
            });
        }
        testService.save(test);
        return "redirect:/tests";
    }


    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("testId") Long id, Model model) {
        Test test = testService.findById(id);
        model.addAttribute("test", test);
        Set<Step> allSteps = stepService.findAll();
        model.addAttribute("allSteps", allSteps);
        return "test-form";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("testId") Long id) {
        testService.deleteById(id);
        return "redirect:/tests";
    }

    @PostMapping("/addStepToTest")
    public String addStepToTest(@RequestParam("stepId") Long stepId, @RequestParam("testId") Long testId, Model model) {
        Test test = testService.findById(testId);
        Step step = stepService.findById(stepId);
        test.addStep(step);
        testService.save(test);

        model.addAttribute("test", test);
        Set<Step> allSteps = stepService.findAll();
        model.addAttribute("allSteps", allSteps);
        return "test-form";
    }
}
