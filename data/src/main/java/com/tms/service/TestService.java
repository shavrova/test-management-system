package com.tms.service;

import com.tms.model.entity.Step;
import com.tms.model.entity.Test;

import java.util.List;

public interface TestService extends CrudService<Test, Long>{

    List<Step> getSteps(Long testId);

    List<Test> getUserTests(Long userId);

    List<Test> getDeletedUserTests();

}
