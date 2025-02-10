package com.fintech.user_wallet_service.service;


import com.fintech.user_wallet_service.exception.UserNotFoundException;
import com.fintech.user_wallet_service.model.User;
import com.fintech.user_wallet_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }
}
