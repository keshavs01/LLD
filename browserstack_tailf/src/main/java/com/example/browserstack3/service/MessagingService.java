package com.example.browserstack3.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {


    private SimpMessagingTemplate simpMessagingTemplate;


    MessagingService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessage(String text) {
        simpMessagingTemplate.convertAndSend("/topic/greetings", text);
    }

}
