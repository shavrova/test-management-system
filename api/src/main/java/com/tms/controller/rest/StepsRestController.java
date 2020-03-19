package com.tms.controller.rest;

import com.tms.model.entity.Step;
import com.tms.service.StepService;
import com.tms.service.TestService;
import com.tms.exception.NotAllowedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class StepsRestController {

    StepService stepService;
    TestService testService;

    public StepsRestController(StepService stepService, TestService testService) {
        this.stepService = stepService;
        this.testService = testService;
    }

    @GetMapping("/steps")
    public List<Step> getSteps() {
        return stepService.findAll();
    }

    @GetMapping("/steps/{stepId}")
    public Step getStep(@PathVariable Long stepId) {
        return stepService.findById(stepId);
    }


    @PostMapping("/steps")
    public Step saveStep(@RequestBody Step step) {
        stepService.save(step);
        return step;
    }


    @PutMapping("/steps")
    public Step updateStep(@RequestBody Step step) {
        stepService.save(step);
        return step;
    }


    @DeleteMapping("/steps/{stepId}")
    public String deleteStep(@PathVariable Long stepId) {
        Step step = stepService.findById(stepId);
        testService.findAll()
                .forEach(test -> {
                    if (test.containsStep(step))
                        throw new NotAllowedException("Can't delete step that is used in test cases.");
                });
        stepService.deleteById(stepId);
        return "Step " + stepId + " was deleted";
    }
}
