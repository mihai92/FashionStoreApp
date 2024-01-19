package com.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.entities.User;
import com.business.repositories.UserRepository;

@Component
public class UserService {

    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get All Users
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        
        for (User user : userRepository.findAll()) {
            users.add(user);
        }

        return users;
}

    // Get Single User
    public User getUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    // Update User
    public void updateUser(User user, int id) {
        user.setUserId(id);
        userRepository.save(user);
    }

    // Delete Single User
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Add User
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userRepository.save(user);
    }
}
