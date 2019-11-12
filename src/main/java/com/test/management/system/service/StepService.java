package com.test.management.system.service;

import com.test.management.system.entity.Step;

import java.util.List;

public interface StepService {

    List<Step> getAllSteps();

    Step getSteps(int stepId);

    void addStep(Step step);

    void deleteStep(int stepId);

    List<Step> getStepsForTest(int testId);
}
