package com.test.management.system.util.wrapper;

import com.test.management.system.entity.Test;
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
