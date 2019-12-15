package com.test.management.system.wrappers;

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
    private String annotations;
}
