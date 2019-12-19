package com.test.management.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tests")
public class Test extends BaseEntity implements Comparable<Test> {

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_description")
    private String testDescription;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(
            mappedBy = "test",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TestStep> steps = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
    }


    public Test(String testName, String testDescription, Category category) {
        this.testName = testName;
        this.testDescription = testDescription;
        this.category = category;
    }

    public void addStep(Step step) {
        TestStep testStep = new TestStep(this, step);
        testStep.setStepOrder(steps.size() + 1);
        steps.add(testStep);
        steps.sort(Comparator.comparing(TestStep::getStepOrder));
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


