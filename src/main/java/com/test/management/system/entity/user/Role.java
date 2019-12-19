package com.test.management.system.entity.user;

import com.test.management.system.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

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