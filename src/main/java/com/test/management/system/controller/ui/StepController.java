package com.test.management.system.controller.ui;

import com.test.management.system.entity.Step;
import com.test.management.system.service.StepService;
import com.test.management.system.util.exception.NotAllowedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class StepController {
    StepService stepService;

    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @PostMapping("/deleteStep")
    public String deleteStep(@RequestParam("stepId") Long id) {
        stepService.deleteById(id);
        return "redirect:/showAllSteps";
    }

    @GetMapping("/showAllSteps")
    public String showAllSteps(Model model) {
        List<Step> allSteps = stepService.findAll();
        model.addAttribute("allSteps", allSteps);
        Step step = new Step();
        model.addAttribute("step", step);
        return "steps-list";
    }

    @PostMapping("/saveStep")
    public String saveStep(@ModelAttribute Step step) {
        stepService.save(step);
        return "redirect:/showAllSteps";
    }

    @PostMapping("/editStep")
    public String editStep(@RequestParam Long stepId, @RequestParam String stepDescription) {
        Step step = stepService.findById(stepId);
        if (stepService.findByDescription(stepDescription) == null) {
            step.setStepDescription(stepDescription);
            stepService.save(step);
        } else {
            throw new NotAllowedException(String.format("Step %s already exists", stepDescription));
        }
        return "redirect:showAllSteps";
    }
}
