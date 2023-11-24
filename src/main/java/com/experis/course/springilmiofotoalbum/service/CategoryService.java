package com.experis.course.springilmiofotoalbum.service;

import com.experis.course.springilmiofotoalbum.exceptions.CategoryNameUniqueException;
import com.experis.course.springilmiofotoalbum.model.Category;
import com.experis.course.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAll() {
    return categoryRepository.findByOrderByName();
  }

  public Category save(Category category) throws CategoryNameUniqueException {
    if (categoryRepository.existsByName(category.getName())) {
      throw new CategoryNameUniqueException(category.getName());
    }
    category.setName(category.getName().toLowerCase());
    return categoryRepository.save(category);
  }

  public Category getCategoryById(Integer id) throws CategoryNameUniqueException {
    Optional<Category> result = categoryRepository.findById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new CategoryNameUniqueException("Category with id " + id + " not found");
    }
  }

  public void deleteCategory(Integer id) {
    categoryRepository.deleteById(id);
  }
}
