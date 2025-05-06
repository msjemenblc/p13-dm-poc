package com.yourcaryourway.dm_poc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcaryourway.dm_poc.dto.request.DirectMessageRequest;
import com.yourcaryourway.dm_poc.dto.response.DirectMessageDTO;
import com.yourcaryourway.dm_poc.model.DirectMessage;
import com.yourcaryourway.dm_poc.model.User;
import com.yourcaryourway.dm_poc.repository.DirectMessageRepository;

@Service
public class DirectMessageService {

    @Autowired
    private DirectMessageRepository directMessageRepository;

    @Autowired
    private UserService userService;
    
    public DirectMessageDTO sendDirectMessage(DirectMessageRequest request) {
        User sender = userService.getUserById(request.getSenderId());
        User receiver = userService.getUserById(request.getReceiverId());

        DirectMessage directMessage = buildDirectMessageEntity(sender, receiver, request.getContent());
        directMessage = directMessageRepository.save(directMessage);

        return convertToDTO(directMessage);
    }

    public List<DirectMessageDTO> getAllDirectMessages() {
        List<DirectMessage> allMessages = directMessageRepository.findAll();
        return allMessages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DirectMessage buildDirectMessageEntity(User sender, User receiver, String content) {
        DirectMessage directMessage = new DirectMessage();

        directMessage.setSender(sender);
        directMessage.setReceiver(receiver);
        directMessage.setContent(content);

        return directMessage;
    }

    private DirectMessageDTO convertToDTO(DirectMessage directMessage) {
        return new DirectMessageDTO(
            directMessage.getId(), 
            directMessage.getSender().getId(), 
            directMessage.getReceiver().getId(), 
            directMessage.getContent(), 
            directMessage.getCreatedAt()
        );
    }

}
