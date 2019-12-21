package com.test.management.system.service;

import com.test.management.system.entity.Category;
import com.test.management.system.repository.CategoryRepository;
import com.test.management.system.util.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

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
        return categoryRepository.findAll();
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
