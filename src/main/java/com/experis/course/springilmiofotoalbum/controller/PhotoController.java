package com.experis.course.springilmiofotoalbum.controller;

import com.experis.course.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

  @Autowired
  private PhotoService photoService;

  // metodo che mostra tutte le foto
  @GetMapping
  public String index(@RequestParam Optional<String> search, Model model) {
    model.addAttribute("photoList", photoService.getPhotoList(search));
    return "photos/list";
  }
}
