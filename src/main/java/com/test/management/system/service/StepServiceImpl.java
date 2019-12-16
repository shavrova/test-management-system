package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.util.exception.ItemNotFoundException;
import com.test.management.system.repository.StepRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StepServiceImpl implements StepService {

    StepRepository stepRepository;

    public StepServiceImpl(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    public SortedSet<Step> findAll() {
        return new TreeSet<>(stepRepository.findAll());
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


    @Override
    public List<Step> findByPartialDescription(String searchKey) {
        return stepRepository.findAll().stream()
                .filter(s -> s.getStepDescription().toLowerCase().contains(searchKey.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Step findByDescription(String stepDescription) {
        return stepRepository.findAll().stream()
                .filter(step -> step.getStepDescription().equalsIgnoreCase(stepDescription.trim()))
                .findAny().orElse(null);
    }
}
