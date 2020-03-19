package com.tms.model.entity.user;

import com.tms.model.entity.BaseEntity;
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