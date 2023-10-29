package com.example.crud.controllers;

import com.example.crud.domain.request.RequestPutUser;
import com.example.crud.domain.request.RequestUser;
import com.example.crud.domain.User;
import com.example.crud.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAllUsers(){
        var allUsers = userRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestUser data){
        User newUser = new User(data);
        userRepository.save(newUser);
        return ResponseEntity.ok(newUser.getName());
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid RequestPutUser data){
        User newUser = userRepository.findByEmailAndPassword(data.email(), data.password());

        if(data.name() != null)
            newUser.setName(data.name());
        if(data.newEmail() != null)
            newUser.setEmail(data.newEmail());
        if(data.newPassword() != null)
            newUser.setEmail(data.newPassword());

        userRepository.save(newUser);
        return newUser;
    }
}