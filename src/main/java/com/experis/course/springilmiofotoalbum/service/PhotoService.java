package com.experis.course.springilmiofotoalbum.service;

import com.experis.course.springilmiofotoalbum.model.Photo;
import com.experis.course.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

  @Autowired
  private PhotoRepository photoRepository;

  // METODO CHE RESTITUISCE LA LISTA DI TUTTE LE FOTO EVENTUALMENTE FILTRATE

  public List<Photo> getPhotoList(Optional<String> search) {
    if (search.isPresent()) {
      return photoRepository.findByTitleContainingIgnoreCase(
          search.get());
    } else {
      return photoRepository.findAll();
    }
  }

  // METODO CHE RESTITUISCE UNA LISTA DI TUTTE LE FOTO
  public List<Photo> getPhotoList() {
    return photoRepository.findAll();
  }
}
