package com.example.masaiatm.controller;

import com.example.masaiatm.dto.LoginDTO;
import com.example.masaiatm.dto.RegisterDTO;
import com.example.masaiatm.entity.Account;
import com.example.masaiatm.entity.User;
import com.example.masaiatm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/masaiAtm/user/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDto){
        boolean isCreated = userService.loginUser(loginDto);
        return isCreated ? new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/masaiAtm/user/accounts")
    public ResponseEntity<List<Account>> getAccounts(){
        List<Account> accounts = userService.getAccounts();
        return accounts != null ? new ResponseEntity<>(accounts, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/masaiAtm/user/account/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId){
        Account account = userService.getAccount(accountId);
        return account != null ? new ResponseEntity<>(account, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/masaiAtm/user/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable String accountId){
        boolean isDeleted =  userService.deleteAccount(accountId);
        return isDeleted ? new ResponseEntity<>("Details updated successfully", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/masaiAtm/welcome")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }
}
