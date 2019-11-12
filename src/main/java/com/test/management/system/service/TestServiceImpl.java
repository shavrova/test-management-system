package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.repository.TestRepository;
import com.test.management.system.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    TestRepository testRepository;

    @Override
    public List<Test> getTests() {
        return testRepository.findAll();
    }

    @Override
    public void saveTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public Test getTest(int testId) {
        Optional<Test> res = testRepository.findById(testId);

        Test test;
        System.out.println();

        if (res.isPresent()) {
            test = res.get();
        } else {
            throw new RuntimeException("Can't find test with id " + testId);
        }

        return test;

    }

    @Override
    public void deleteTest(int testId) {
        testRepository.deleteById(testId);
    }

    @Override
    public List<Step> getSteps(int testId) {
        Optional<Test> res = testRepository.findById(testId);

        List<Step> steps;

        if (res.isPresent()) {
            steps = res.get().getSteps();
        } else {
            throw new RuntimeException("Can't find steps for test id " + testId);
        }

        return steps;
    }
}





