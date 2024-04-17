package com.academy.finalproject.service;

import com.academy.finalproject.entity.Item;
import com.academy.finalproject.entity.User;
import com.academy.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User getUser(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    public User updateUser(User user, Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()){
            return null;
        }
        User validObject = foundUser.get();
        validObject.setFirstName(user.getFirstName());
        validObject.setLastName(user.getLastName());
        validObject.setAge(user.getAge());
        validObject.setEmail(user.getEmail());
        validObject.setPassword(user.getPassword());

        userRepository.save(validObject);
        return validObject;
    }

    public User deleteUserById(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()){
            return null;
        }
        userRepository.deleteById(id);
        return foundUser.get();
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

}

