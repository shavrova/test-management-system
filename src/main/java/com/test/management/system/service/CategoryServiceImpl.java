package com.test.management.system.service;

import com.test.management.system.entity.Category;
import com.test.management.system.exception.ItemNotFoundException;
import com.test.management.system.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findAll() {
        return new TreeSet<>(categoryRepository.findAll());
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
