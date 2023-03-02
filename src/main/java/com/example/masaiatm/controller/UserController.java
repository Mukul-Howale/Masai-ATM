package com.example.masaiatm.controller;

import com.example.masaiatm.dto.LoginDTO;
import com.example.masaiatm.dto.RegisterDTO;
import com.example.masaiatm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // Register new user
    @PostMapping("/masaiAtm/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){
        boolean isCreated = userService.registerUser(registerDTO);
        return isCreated ? new ResponseEntity<>("User registered successfully",HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("//masaiAtm/user/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDto){
        boolean isCreated = userService.loginUser(loginDto);
        return isCreated ? new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
