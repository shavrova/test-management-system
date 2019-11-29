package com.test.management.system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tests")
public class Test extends BaseEntity implements Comparable<Test>{

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_description")
    private String testDescription;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "create_date")
//    private Date createDate;

    @OneToMany(
            mappedBy = "test",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TestStep> steps = new ArrayList<>();

    public Test(String testName, String testDescription) {
        this.testName = testName;
        this.testDescription = testDescription;
    }

    public void addStep(Step step) {
        TestStep testStep = new TestStep(this, step);
        testStep.setStepOrder(steps.size() + 1);
        steps.add(testStep);

    }

    public boolean containsStep(Step step) {
        return steps.stream()
                .anyMatch(s -> s.getId().getStepId() == step.getId());
    }


    private void fixStepsOrder() {
        steps.stream().forEach(s -> s.setStepOrder(steps.indexOf(s) + 1));
    }

    public void removeStep(Step step) {
        steps.removeIf((TestStep ts) -> ts.getTest().equals(this) &&
                ts.getStep().equals(step));
        fixStepsOrder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Test test = (Test) o;
        return Objects.equals(this.testName, test.testName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.testName) + 45;
    }

    @Override
    public int compareTo(Test test) {
        return this.getId().compareTo(test.getId());
    }
}


