package com.experis.course.springilmiofotoalbum.exceptions;

public class PhotoNotFoundException extends RuntimeException {
  public PhotoNotFoundException(String message) {
    super(message);
  }
}
