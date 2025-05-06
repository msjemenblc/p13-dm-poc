package com.yourcaryourway.dm_poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcaryourway.dm_poc.exception.UserNotFoundException;
import com.yourcaryourway.dm_poc.model.User;
import com.yourcaryourway.dm_poc.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
