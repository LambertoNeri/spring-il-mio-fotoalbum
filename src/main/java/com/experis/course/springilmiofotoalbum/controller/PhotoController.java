package com.experis.course.springilmiofotoalbum.controller;

import com.experis.course.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.experis.course.springilmiofotoalbum.exceptions.UserNotFoundException;
import com.experis.course.springilmiofotoalbum.model.Photo;
import com.experis.course.springilmiofotoalbum.repository.UserRepository;
import com.experis.course.springilmiofotoalbum.security.DatabaseUserDetails;
import com.experis.course.springilmiofotoalbum.service.CategoryService;
import com.experis.course.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

  @Autowired
  private CategoryService categoryService;
  @Autowired
  private PhotoService photoService;

  @Autowired
  private UserRepository userRepository;


  // metodo che mostra tutte le foto
  @GetMapping
  public String index(@RequestParam Optional<String> search, Model model, Authentication authentication) {
    DatabaseUserDetails user = (DatabaseUserDetails) authentication.getPrincipal();
    model.addAttribute("photoList", photoService.getPhotoList(search, user.getId()));
    return "photos/list";
  }

  // metodo che mostra i dettagli di una foto presa per id
  @GetMapping("/show/{id}")
  public String show(@PathVariable Integer id, Model model) {
    try {
      Photo photo = photoService.getPhotoById(id);
      model.addAttribute("photo", photo);
      return "photos/show";
    } catch (PhotoNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }


  // metodo che mostra il form di creazione della foto
  @GetMapping("/create")
  public String create(Model model, Authentication authentication) {
    DatabaseUserDetails user = (DatabaseUserDetails) authentication.getPrincipal();
    Photo p = new Photo();
    model.addAttribute("user", user.getId());
    model.addAttribute("photo", p);
    model.addAttribute("categoryList", categoryService.getAll());
    return "photos/form";
  }

  @PostMapping("/create")
  public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto,
                         BindingResult bindingResult, Model model, Authentication authentication) {

    DatabaseUserDetails user = (DatabaseUserDetails) authentication.getPrincipal();


    if (bindingResult.hasErrors()) {
      model.addAttribute("categoryList", categoryService.getAll());
      System.out.println(bindingResult);
      return "photos/form";
    } else {
      try {
        Photo savedPhoto = photoService.createPhoto(formPhoto, user.getId());
        return "redirect:/photos/show/" + savedPhoto.getId();
      } catch (UserNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      }
    }
  }

  // metodo che mostra la pagida di modifica di una foto
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model, Authentication authentication) {
    DatabaseUserDetails userDetails = (DatabaseUserDetails) authentication.getPrincipal();
    Integer photoID = photoService.getPhotoById(id).getUser().getId();
    Integer userId = userDetails.getId();
    if (userDetails.isSuperAdmin() || userId.equals(photoID)) {
      try {
        model.addAttribute("photo", photoService.getPhotoById(id));
        model.addAttribute("categoryList", categoryService.getAll());
        return "photos/form";
      } catch (PhotoNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      }
    }
    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Canno't edit other's users photos");
  }

  @PostMapping("/edit/{id}")
  public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto,
                       BindingResult bindingResult, Model model, Authentication authentication) {
    DatabaseUserDetails userDetails = (DatabaseUserDetails) authentication.getPrincipal();
    Integer photoID = photoService.getPhotoById(id).getUser().getId();
    Integer userId = userDetails.getId();
    if (userDetails.isSuperAdmin() || userId.equals(photoID)) {
      if (bindingResult.hasErrors()) {
        model.addAttribute("categoryList", categoryService.getAll());
        return "/photos/form";
      }
      Photo savedPhoto = photoService.editPhoto(formPhoto);
      return "redirect:/photos/show/" + savedPhoto.getId();
    }
    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Canno't edit other's users photos");
  }

  // metodo per eliminare una foto da database

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes, Authentication authentication) {
    DatabaseUserDetails userDetails = (DatabaseUserDetails) authentication.getPrincipal();
    Integer photoID = photoService.getPhotoById(id).getUser().getId();
    Integer userId = userDetails.getId();
    if (userDetails.isSuperAdmin() || userId.equals(photoID)) {
      try {
        Photo photoToDelete = photoService.getPhotoById(id);
        photoService.deletePhoto(id);
        redirectAttributes.addFlashAttribute("message",
            "Photo " + photoToDelete.getTitle() + " deleted!");
        return "redirect:/photos";
      } catch (PhotoNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      }
    }
    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Canno't edit other's users photos");
  }
}
