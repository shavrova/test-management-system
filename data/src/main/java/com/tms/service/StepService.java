package com.tms.service;

import com.tms.model.entity.Step;

import java.util.List;

public interface StepService extends CrudService<Step, Long> {

    List<Step> findByPartialDescription(String q);

    Step findByDescription(String description);

}
