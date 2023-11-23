package com.experis.course.springilmiofotoalbum.exceptions;

public class CategoryNameUniqueException extends RuntimeException {
  public CategoryNameUniqueException(String message) {
    super(message);
  }
}
