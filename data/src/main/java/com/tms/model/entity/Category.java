package com.tms.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends BaseEntity implements Comparable<Category>{

    @Column(name = "category_name")
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int compareTo(Category category) {
        return this.getId().compareTo(category.getId());
    }


}
