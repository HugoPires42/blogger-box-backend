package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public List<Category> getAllByName(String name) {
        if (name == null || name.isBlank()) {
            return getAll();
        }
        return categories.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public Category getById(UUID id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(name);
        categories.add(category);
        return category;
    }

    @Override
    public Category update(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
        }
        return category;
    }

    @Override
    public boolean deleteById(UUID id) {
        return categories.removeIf(c -> c.getId().equals(id));
    }
}
