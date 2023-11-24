package com.experis.course.springilmiofotoalbum.repository;

import com.experis.course.springilmiofotoalbum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

  List<Message> findByOrderByTitle();

  boolean existsByTitle(String name);
}
