package com.experis.course.springilmiofotoalbum.controller;

import com.experis.course.springilmiofotoalbum.security.DatabaseUserDetails;
import com.experis.course.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("")
public class EmptyController {

  @Autowired
  PhotoService photoService;

  @GetMapping
  public String index(@RequestParam Optional<String> search, Model model, Authentication authentication) {
    DatabaseUserDetails user = (DatabaseUserDetails) authentication.getPrincipal();
    model.addAttribute("photoList", photoService.getPhotoList(search, user.getId()));
    return "photos/list";
  }
}
