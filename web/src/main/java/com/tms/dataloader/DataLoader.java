//package com.tms.dataloader;
//
//import com.tms.model.entity.Category;
//import com.tms.service.CategoryService;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements ApplicationRunner {
//
//    private CategoryService categoryService;
//
//    public DataLoader(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    public void run(ApplicationArguments args) {
//        try {
//            categoryService.save(new Category("None"));
//        } catch (Exception ex) {
//        }
//    }
//}