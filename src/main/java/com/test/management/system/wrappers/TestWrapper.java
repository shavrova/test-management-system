package com.test.management.system.wrappers;

import com.test.management.system.entity.Test;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestWrapper {
    private List<Test> list;

//    public List<Test> getList() {
//        if (this.list == null) {
//            this.list = new ArrayList<>();
//        }
//        return list;
//    }

//    public void setList(List<Test> list) {
//        this.list = list;
//    }
//
//    public Wrapper(List<Test> list) {
//        this.list = list;
//    }
}
