package com.experis.course.springilmiofotoalbum.service;

import com.experis.course.springilmiofotoalbum.exceptions.PhotoNotFoundException;
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

  // METODO PER CREARE UNA NUOVA FOTO

  public Photo createPhoto(Photo photo) {
    photo.setId(null);
    return photoRepository.save(photo);
  }

  // METODO CHE RESTITUISCE UNA FOTO PRESA PER ID, SE NON LA TROVA SOLLEVA UN'ECCEZIONE
  public Photo getPhotoById(Integer id) throws PhotoNotFoundException {
    Optional<Photo> result = photoRepository.findById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new PhotoNotFoundException("Photo with id " + id + " not found");
    }
  }

  // METODO PER MODIFICARE UNA FOTO CON UN ID
  public Photo editPhoto(Photo photo) throws PhotoNotFoundException {
    Photo photoToEdit = getPhotoById(photo.getId());
    photoToEdit.setTitle(photo.getTitle());
    photoToEdit.setUrl(photo.getUrl());
    photoToEdit.setDescription(photo.getDescription());
    photoToEdit.setCategories(photo.getCategories());
    photoToEdit.setVisibility(photo.getVisibility());

    return photoRepository.save(photoToEdit);
  }

  // METODO CHE ELIMINA UNA FOTO DAL DATABASE
  public void deletePhoto(Integer id) {
    photoRepository.deleteById(id);
  }

}
