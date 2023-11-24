package com.experis.course.springilmiofotoalbum.api;

import com.experis.course.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.experis.course.springilmiofotoalbum.model.Photo;
import com.experis.course.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/photos")
@CrossOrigin
public class PhotoRestController {

  @Autowired
  private PhotoService photoService;

  // endpoint per la lista di tutte le foto
  @GetMapping
  public List<Photo> index(@RequestParam Optional<String> search) {
    return photoService.getPhotoList(search);
  }

  // endpoint per i dettagli della foto presa per id
  @GetMapping("/{id}")
  public Photo details(@PathVariable Integer id) {
    try {
      return photoService.getPhotoById(id);
    } catch (PhotoNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  // endpoint per creare una nuova foto
  @PostMapping
  public Photo create(@Valid @RequestBody Photo photo) {
    return photoService.createPhoto(photo);
  }

  // endpoint per modificare una foto
  @PutMapping("/{id}")
  public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo photo) {
    photo.setId(id);
    try {
      return photoService.editPhoto(photo);
    } catch (PhotoNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // endpoint per cancellare una foto
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    try {
      Photo photoToDelete = photoService.getPhotoById(id);
      photoService.deletePhoto(id);
    } catch (PhotoNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // endpoint di test per lista paginata
  @GetMapping("/page")
  public Page<Photo> pagedIndex(
      @RequestParam(name = "size", defaultValue = "2") Integer size,
      @RequestParam(name = "page", defaultValue = "0") Integer page) {
    return photoService.getPage(PageRequest.of(page, size));
  }

  @GetMapping("/page/v2")
  public Page<Photo> pagedIndexV2(@PageableDefault(page = 0, size = 2) Pageable pageable) {
    return photoService.getPage(pageable);
  }
}
