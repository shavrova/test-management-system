package com.test.management.system.service;

import com.test.management.system.entity.Step;
import com.test.management.system.entity.Test;

import java.util.List;

public interface TestService extends CrudService<Test, Long>{

    List<Step> getSteps(Long testId);

    List<Test> getUserTests(Long userId);

    List<Test> getDeletedUserTests();

}
