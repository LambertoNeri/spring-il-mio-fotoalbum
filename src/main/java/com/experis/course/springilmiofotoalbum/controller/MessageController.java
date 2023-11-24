package com.experis.course.springilmiofotoalbum.controller;

import com.experis.course.springilmiofotoalbum.model.Message;
import com.experis.course.springilmiofotoalbum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class MessageController {
  @Autowired
  MessageService messageService;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("messageList", messageService.getAll());
    model.addAttribute("messageObj", new Message());
    return "messages/index";
  }
}
