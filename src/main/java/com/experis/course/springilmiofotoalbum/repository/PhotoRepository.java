package com.experis.course.springilmiofotoalbum.repository;

import com.experis.course.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
  List<Photo> findByTitleContainingIgnoreCase(String nameKeyword);

  List<Photo> findByVisibilityTrueAndTitleContainingIgnoreCase(String nameKeyword);

  List<Photo> findByVisibilityTrue();

  List<Photo> findByUserIdAndTitleContainingIgnoreCase(Integer userId, String nameKeyword);

  List<Photo> findByUserId(Integer userId);
}
