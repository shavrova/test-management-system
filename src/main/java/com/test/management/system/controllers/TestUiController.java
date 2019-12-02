package com.test.management.system.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.service.CategoryService;
import com.test.management.system.service.StepService;
import com.test.management.system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/")
public class TestUiController {

    @Autowired
    TestService testService;

    @Autowired
    StepService stepService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/tests")
    public String getTests(Model model) {
            Set<Test> allTests = testService.findAll();
            model.addAttribute("tests", allTests);
            Test test = new Test();
            model.addAttribute("test", test);
            model.addAttribute("categories", categoryService.findAll());
        return "tests-list";
    }

    @PostMapping("/addTest")
    public String addTest(@ModelAttribute Test test, BindingResult bindingResult) {
          testService.save(test);
        return "redirect:/tests";
    }

    @PostMapping("/save")
    public String saveTest(
            @ModelAttribute @Valid Test test,
            BindingResult bindingResult,
            @RequestParam(required = false) Set<String> description) {

        if(description != null) {
            description
                    .stream()
                    .forEach(s -> {
                        if (stepService.findByDescription(s) != null) {
                            test.addStep(stepService.findByDescription(s));
                        } else {
                            Step step = new Step(s);
                            stepService.save(step);
                            test.addStep(step);
                        }
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
        model.addAttribute("categories", categoryService.findAll());

        return "test-form";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("testId") Long id) {
        testService.deleteById(id);
        return "redirect:/tests";
    }

    @GetMapping(value = "/search")
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

//    @ExceptionHandler({NotAllowedException.class})
//    public ModelAndView getSuperheroesUnavailable(NotAllowedException ex) {
//        return new ModelAndView("tests-list", "error", ex.getMessage());
//    }
}
