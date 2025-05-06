package com.yourcaryourway.dm_poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcaryourway.dm_poc.dto.request.DirectMessageRequest;
import com.yourcaryourway.dm_poc.dto.response.DirectMessageDTO;
import com.yourcaryourway.dm_poc.service.DirectMessageService;

@RestController
@RequestMapping("/api/messages")
public class DirectMessageController {

    @Autowired
    private DirectMessageService directMessageService;

    @PostMapping
    public ResponseEntity<DirectMessageDTO> sendDirectMessage(@RequestBody DirectMessageRequest request) {
       DirectMessageDTO response = directMessageService.sendDirectMessage(request);
       return new ResponseEntity<>(response, HttpStatus.CREATED); 
    }

    @GetMapping
    public ResponseEntity<List<DirectMessageDTO>> getAllDirectMessages() {
        List<DirectMessageDTO> response = directMessageService.getAllDirectMessages();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
