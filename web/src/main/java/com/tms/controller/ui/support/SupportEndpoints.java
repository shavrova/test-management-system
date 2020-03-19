package com.tms.controller.ui.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.model.entity.Step;
import com.tms.model.entity.Test;
import com.tms.service.StepService;
import com.tms.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/")
public class SupportEndpoints {

    TestService testService;
    StepService stepService;

    public SupportEndpoints(TestService testService, StepService stepService) {
        this.testService = testService;
        this.stepService = stepService;
    }

    @GetMapping("/search")
    public ResponseEntity<String> getAllSteps(@RequestParam("q") String input) {
        List<String> result = stepService.findByPartialDescription(input)
                .stream()
                .map(Step::getStepDescription)
                .collect(Collectors.toList());
        String resp = "";
        try {
            resp = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/tests/{testId}/steps/{stepId}")
    public String deleteStepFromTest(@PathVariable Long testId, @PathVariable Long stepId) {
        Test test = testService.findById(testId);
        Step step = stepService.findById(stepId);
        test.removeStep(step);
        testService.save(test);
        return "Step was deleted.";
    }
}
