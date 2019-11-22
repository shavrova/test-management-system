package com.test.management.system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "steps")
public class Step extends BaseEntity{

    @Column(name = "step_description")
    private String stepDescription;

    public Step(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(this.stepDescription, step.stepDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stepDescription)+56;
    }

}
