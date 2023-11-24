package com.experis.course.springilmiofotoalbum.service;

import com.experis.course.springilmiofotoalbum.exceptions.MessageTitleUniqueException;
import com.experis.course.springilmiofotoalbum.model.Message;
import com.experis.course.springilmiofotoalbum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messageRepository;

  // metodo per ricevcere una lista di tutti i messaggi
  public List<Message> getAll() {
    return messageRepository.findByOrderByTitle();
  }

  public List<Message> getMessageList() {
    return messageRepository.findAll();
  }

  // metodo per creare un nuovo messaggio
  public Message createMessage(Message message) {
    if (messageRepository.existsByTitle(message.getTitle())) {
      throw new MessageTitleUniqueException(message.getTitle());
    }
    message.setId(null);
    return messageRepository.save(message);
  }

  // metodo per modificare un messaggio
  public Message editMessage(Message message) throws MessageTitleUniqueException {
    if (messageRepository.existsByTitle(message.getTitle())) {
      throw new MessageTitleUniqueException(message.getTitle());
    }
    message.setTitle(message.getTitle().toLowerCase());
    return messageRepository.save(message);
  }


  // metodo per ricevere un messaggio da id
  public Message getMessageById(Integer id) throws MessageTitleUniqueException {
    Optional<Message> result = messageRepository.findById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new MessageTitleUniqueException("Message with id " + id + " not found");
    }
  }

  // metodo per cancellare un messaggio
  public void deleteMessage(Integer id) {
    messageRepository.deleteById(id);
  }
}
