package com.test.management.system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min=2, max=100)
    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_description")
    private String testDescription;



    @ManyToMany(fetch=FetchType.EAGER,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="test_step",
               joinColumns=@JoinColumn(name="test_id"),
               inverseJoinColumns=@JoinColumn(name="step_id"))
    private List<Step> steps;


    public Test(String testName, String testDescription) {
        this.testName = testName;
        this.testDescription = testDescription;
    }

    public void addStep(Step step) {

        if (step == null) {
            steps = new ArrayList<>();
        }

        steps.add(step);
    }

}


