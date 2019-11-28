package com.test.management.system.service;

import com.test.management.system.entity.Step;

import java.util.List;

public interface StepService extends CrudService<Step, Long> {

    List<Step> findByPartialDescription(String q);

    Step findByDescription(String description);

}
