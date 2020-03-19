package com.tms.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tms.model.entity.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tests")
public class Test extends BaseEntity implements Comparable<Test> {

    @NotNull
    @Size(min = 2,
            max = 255,
            message = "Name should be at least {min} characters long, but no more than {max}.")
    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_description")
    private String testDescription;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "test",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
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

    public Test(String testName, String testDescription, Date date, Category category, User user) {
        this.testName = testName;
        this.testDescription = testDescription;
        this.createDate = date;
        this.category = category;
        this.user = user;
    }


    public void addStep(Step step) {
        TestStep testStep = new TestStep(this, step);
        if (steps == null)
            steps = new ArrayList<>();
        testStep.setStepOrder(steps.size() + 1);
        steps.add(testStep);
        Collections.sort(steps);
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


