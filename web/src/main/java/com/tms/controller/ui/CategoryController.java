package com.tms.controller.ui;

import com.tms.model.entity.Category;
import com.tms.model.entity.Test;
import com.tms.service.CategoryService;
import com.tms.service.TestService;
import com.tms.wrapper.TestWrapper;
import com.tms.generator.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/")
public class CategoryController {

    CategoryService categoryService;
    TestService testService;
    FileService fileService;

    public CategoryController(CategoryService categoryService, TestService testService, FileService fileService) {
        this.categoryService = categoryService;
        this.testService = testService;
        this.fileService = fileService;
    }

    @PostMapping("/deleteCat")
    public String deleteCategory(@RequestParam("categoryId") Long id) {
        categoryService.deleteById(id);
        return "redirect:/showAllCat";
    }

    @GetMapping("/showAllCat")
    public String showAllCategories(Model model) {
        List<Category> all = categoryService.findAll();
        model.addAttribute("allCategories", all);
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories-list";
    }

    @PostMapping("/saveCat")
    public String saveCat(@ModelAttribute Category category, BindingResult bindingResult) {
        categoryService.save(category);
        return "redirect:/showAllCat";
    }

    @PostMapping("/generateFeatureFile")
    public String generate(@RequestParam("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        List<Test> allTests = testService.findAll();
        List<Test> result = allTests.stream().filter(s -> s.getCategory().equals(category) && !s.getSteps().isEmpty()).collect(Collectors.toList());
        TestWrapper wrapper = new TestWrapper();
        wrapper.setList(result);
        wrapper.setFeatureName(category.getCategoryName());
        model.addAttribute("wrapper", wrapper);
        return "download.html";
    }

    @GetMapping(value = "/downloadFile", params = "action=feature")
    public ResponseEntity<InputStreamResource> downloadFeatureFile(
            @ModelAttribute("wrapper") @Valid TestWrapper testWrapper,
            BindingResult result)
            throws IOException {
        File file = fileService.createFeatureFile(testWrapper);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.TEXT_PLAIN);
        respHeaders.setContentDispositionFormData("attachment", testWrapper.getFeatureName() + ".feature");
        InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
        return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/downloadFile", params = "action=java")
    public ResponseEntity<InputStreamResource> downloadJavaFile(
            @ModelAttribute("wrapper") TestWrapper testWrapper)
            throws IOException {
        File file = fileService.createJavaFile(testWrapper);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.TEXT_PLAIN);
        respHeaders.setContentDispositionFormData("attachment", testWrapper.getFeatureName() + "Steps" + ".java");
        InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
        return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
    }

}
