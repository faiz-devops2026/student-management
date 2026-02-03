package com.example.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendStudentUpdate(String message) {
        template.convertAndSend("/topic/students", message);
    }
}
