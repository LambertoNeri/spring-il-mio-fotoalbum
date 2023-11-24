package com.experis.course.springilmiofotoalbum.controller;

import com.experis.course.springilmiofotoalbum.exceptions.CategoryNameUniqueException;
import com.experis.course.springilmiofotoalbum.model.Category;
import com.experis.course.springilmiofotoalbum.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("categoryList", categoryService.getAll());
    model.addAttribute("categoryObj", new Category());
    return "categories/index";
  }

  @PostMapping
  public String doSave(@Valid @ModelAttribute("categoryObj") Category formCategory,
                       BindingResult bindingResult,
                       Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("categoryList", categoryService.getAll());
      return "categories/index";
    }
    try {
      categoryService.save(formCategory);
      return "redirect:/categories";
    } catch (CategoryNameUniqueException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A category with name " + e.getMessage() + " already exists");
//      model.addAttribute("categoryList", categoryService.getAll());
//      return "categories/index";
    }
  }
}
