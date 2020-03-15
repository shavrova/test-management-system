package com.test.management.system.entity.user;

import com.test.management.system.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Role extends BaseEntity {

    private String name;

    public Role(String name) {
        this.name = name;
    }


}