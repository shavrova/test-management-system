package com.tms.service;

import com.tms.model.entity.Category;
import com.tms.repository.CategoryRepository;
import com.tms.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        List<Category> all = categoryRepository.findAll();
        Collections.sort(all);
        return all;
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> res = categoryRepository.findById(id);
        Category category;
        if (res.isPresent()) {
            category = res.get();
        } else {
            throw new ItemNotFoundException("Can't find category with id " + id);
        }
        return category;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
