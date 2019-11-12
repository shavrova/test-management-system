package com.test.management.system.rest;

import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.exception.TestNotFoundException;
import com.test.management.system.service.StepService;
import com.test.management.system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TestRestController {


    @Autowired
    private TestService testService;

    @Autowired
    private StepService stepService;


    @GetMapping("/tests")
    public List<Test> getTests() {
        return testService.getTests();
    }


    @GetMapping("/tests/{testId}")
    public Test getTest(@PathVariable int testId) {
        Test test = testService.getTest(testId);
        if (test == null) {
            throw new TestNotFoundException("Test id not found - " + testId);
        }
        return test;
    }


    @PostMapping("/tests")
    public Test addTest(@RequestBody Test test) {
        test.setId(0);
        testService.saveTest(test);
        return test;
    }


    @PutMapping("/tests")
    public Test updateTest(@RequestBody Test test) {
        testService.saveTest(test);
        return test;

    }


    @DeleteMapping("/tests/{testId}")
    public String deleteTest(@PathVariable int testId) {
        Test tempTest = testService.getTest(testId);
        if (tempTest == null) {
            throw new TestNotFoundException("Test id not found - " + testId);
        }
        testService.deleteTest(testId);
        return "Test " + testId + "was deleted";
    }


    @GetMapping("/steps/{testId}")
    public List<Step> getStepsForTest(@PathVariable int testId) {

        return stepService.getStepsForTest(testId);
    }

    @ModelAttribute("steps")
    public List<Step> getSteps() {
        return stepService.getAllSteps();
    }
}