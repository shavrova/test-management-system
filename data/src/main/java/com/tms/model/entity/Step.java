package com.tms.model.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "steps")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Step extends BaseEntity implements Comparable<Step>{

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

    @Override
    public int compareTo(Step step) {
        return this.getId().compareTo(step.getId());
    }
}
