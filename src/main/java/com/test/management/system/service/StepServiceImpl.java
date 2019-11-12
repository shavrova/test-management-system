package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.exception.TestNotFoundException;
import com.test.management.system.repository.StepRepository;
import com.test.management.system.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StepServiceImpl implements StepService {

    @Autowired
    StepRepository stepRepository;

    @Autowired
    TestService testService;

    @Override
    public List<Step> getAllSteps() {
        return stepRepository.findAll();
    }

    @Override
    public Step getSteps(int stepId) {
        Optional<Step> res = stepRepository.findById(stepId);

        Step step;

        if (res.isPresent()) {
            step = res.get();
        } else {
            throw new RuntimeException("Can't find test with id " + stepId);
        }
        return step;

    }

    @Override
    public void addStep(Step step) {
        stepRepository.save(step);
    }

    @Override
    public void deleteStep(int stepId) {
        stepRepository.deleteById(stepId);
    }

    @Override
    public List<Step> getStepsForTest(int testId) {

        Test test = testService.getTest(testId);

        if (test == null) {
            throw new TestNotFoundException("Test id not found - " + testId);
        }
        List<Step> steps = test.getSteps();
        if (steps == null) {
            throw new TestNotFoundException(String.format("Test %s doesn't contain steps", testId));
        }
        return steps;

    }

}
