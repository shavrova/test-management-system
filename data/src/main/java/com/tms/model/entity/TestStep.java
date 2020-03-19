package com.tms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "TestStep")
@Table(name = "test_step")
public class TestStep implements Comparable<TestStep> {

    @EmbeddedId
    private TestStepId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("testId")
    private Test test;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("stepId")
    private Step step;

    @Column(name = "step_order")
    private Integer stepOrder;

    @Column(name="step_usecase")
    private String stepUsecase;

    TestStep(Test test, Step step) {
        this.test = test;
        this.step = step;
        this.id = new TestStepId(test.getId(), step.getId());
        this.stepUsecase = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        TestStep that = (TestStep) o;
        return Objects.equals(test, that.test) &&
                Objects.equals(step, that.step);
    }

    @Override
    public int hashCode() {
        return Objects.hash(test, step) ;
    }

    @Override
    public int compareTo(TestStep ts) {
        return this.getStepOrder().compareTo(ts.getStepOrder());
}
}
