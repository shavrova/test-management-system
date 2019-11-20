package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.entity.TestStep;
import com.test.management.system.exception.ItemNotFoundException;
import com.test.management.system.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<Step> getSteps(Long testId) {
        Test test = testRepository.getOne(testId);
        if (test == null) {
            throw new ItemNotFoundException("Test id not found - " + testId);
        }
        return test.getSteps()
                .stream()
                .map(TestStep::getStep)
                .peek(p -> {
                    if (p == null)
                        throw new ItemNotFoundException(String.format("Test %s doesn't contain steps", testId));
                })
                .collect(Collectors.toList());
    }

    @Override
    public Set<Test> findAll() {
        return new HashSet<>(testRepository.findAll());
    }

    @Override
    public Test findById(Long testId) {
        Optional<Test> res = testRepository.findById(testId);

        Test test;

        if (res.isPresent()) {
            test = res.get();
        } else {
            throw new ItemNotFoundException("Can't find test with id " + testId);
        }

        return test;
    }

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public void deleteById(Long testId) {
        testRepository.deleteById(testId);
    }
}





