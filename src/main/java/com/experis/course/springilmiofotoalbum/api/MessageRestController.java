package com.experis.course.springilmiofotoalbum.api;

import com.experis.course.springilmiofotoalbum.model.Message;
import com.experis.course.springilmiofotoalbum.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin
public class MessageRestController {
  @Autowired
  private MessageService messageService;

  //endpoint per la lista di tutti i messaggi
  @GetMapping
  public List<Message> index() {
    return messageService.getMessageList();
  }

  // endpoint per creare un nuovo messaggio

  @PostMapping
  public Message create(@Valid @RequestBody Message message) {
    return messageService.createMessage(message);
  }
}
