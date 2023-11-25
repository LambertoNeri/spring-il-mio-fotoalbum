package com.experis.course.springilmiofotoalbum.service;

import com.experis.course.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import com.experis.course.springilmiofotoalbum.model.Photo;
import com.experis.course.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  // METODO CHE RESTITUISCE UNA LISTA DI TUTTE LE FOTO VISIBILI EVENTUALMENTE FILTRATE
  public List<Photo> getVisiblePhotoList(Optional<String> search) {
    if (search.isPresent()) {
      return photoRepository.findByVisibilityTrueAndTitleContainingIgnoreCase(
          search.get());
    } else {
      return photoRepository.findByVisibilityTrue();
    }
  }
//  public List<Photo> getVisiblePhotoList(Optional<String> search) {
//    if (search.isPresent()) {
//      List<Photo> photoListUnfiltered = photoRepository.findByTitleContainingIgnoreCase(search.get());
//      List<Photo> photoList = new ArrayList<>();
//      for (Photo photo : photoListUnfiltered) {
//        if (photo.getVisibility() == true) {
//          photoList.add(photo);
//        }
//      }
//      return photoList;
//    } else {
//      List<Photo> photoListUnfiltered = photoRepository.findAll();
//      List<Photo> photoList = new ArrayList<>();
//      for (Photo photo : photoListUnfiltered) {
//        if (photo.getVisibility() == true) {
//          photoList.add(photo);
//        }
//      }
//      return photoList;
//    }
//  }


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

  // METODO CHE PRENDE IN INGRESSO UN Pageable e restituisce la Page di libri

  public Page<Photo> getPage(Pageable pageable) {
    return photoRepository.findAll(pageable);
  }

}
