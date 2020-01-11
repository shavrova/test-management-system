package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;
import com.test.management.system.entity.TestStep;
import com.test.management.system.repository.TestRepository;
import com.test.management.system.util.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public List<Test> findAll() {
        List<Test> all = testRepository.findAll();
        Collections.sort(all);
        return all;
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

    @Override
    public List<Test> getUserTests(Long userId) {
        List<Test> userTest = new ArrayList<>();
        for (Test test : testRepository.findAll()) {
            if (test.getUser() != null && test.getUser().getId().equals(userId)) {
                userTest.add(test);
            }
        }
        return userTest;
    }

    @Override
    public List<Test> getDeletedUserTests() {
        List<Test> userTest = new ArrayList<>();
        for (Test test : testRepository.findAll()) {
            System.out.println("size: " + testRepository.findAll().size());
            System.out.println("test:" + test);
            if (test.getUser() == null) {
                userTest.add(test);
            }
        }
        return userTest;
    }
}





