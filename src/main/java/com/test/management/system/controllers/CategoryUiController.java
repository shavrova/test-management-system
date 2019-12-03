package com.test.management.system.controllers;

import com.test.management.system.entity.Category;
import com.test.management.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.SortedSet;

@Controller
@RequestMapping(path = "/")
public class CategoryUiController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/deleteCat")
    public String deleteCategory(@RequestParam("categoryId") Long id) {
        categoryService.deleteById(id);
        return "redirect:/showAllCat";
    }

    @GetMapping("/showAllCat")
    public String showAllCategories(Model model) {
        SortedSet<Category> all = categoryService.findAll();
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
}
