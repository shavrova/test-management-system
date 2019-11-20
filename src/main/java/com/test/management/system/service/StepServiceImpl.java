package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.exception.ItemNotFoundException;
import com.test.management.system.repository.StepRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StepServiceImpl implements StepService {

    StepRepository stepRepository;

    public StepServiceImpl(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    public Set<Step> findAll() {
        return new HashSet<>(stepRepository.findAll());
    }

    @Override
    public Step findById(Long stepId) {
        Optional<Step> res = stepRepository.findById(stepId);

        Step step;

        if (res.isPresent()) {
            step = res.get();
        } else {
            throw new ItemNotFoundException("Can't find step with id " + stepId);
        }
        return step;
    }

    @Override
    public Step save(Step step) {
        return stepRepository.save(step);
    }

    @Override
    public void deleteById(Long stepId) {
        stepRepository.deleteById(stepId);
    }
}
