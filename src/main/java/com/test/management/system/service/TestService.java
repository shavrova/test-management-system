package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;

import java.util.List;

public interface TestService {

    List<Test> getTests();

    void saveTest(Test theTest);

    Test getTest(int theId);

    void deleteTest(int theId);

    List<Step> getSteps(int testId);

}
