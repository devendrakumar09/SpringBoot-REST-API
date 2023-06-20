package com.spring.student.controllers;

import com.spring.student.models.UserModel;
import com.spring.student.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserRepo userRepo;


    @GetMapping
    public List<UserModel> findAllUSers(){
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable(value = "id") long id) {
        Optional<UserModel> user = userRepo.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public UserModel saveUser(@Validated @RequestBody UserModel user) {
        return userRepo.save(user);
    }
}
