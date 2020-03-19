package com.tms.wrapper;

import com.tms.model.entity.Test;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestWrapper {

    private List<Test> list;

    private String featureName;
//TODO: FIX THIS
    //@CucumberAnnotations
    private String annotations;
}
