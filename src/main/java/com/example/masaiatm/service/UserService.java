package com.example.masaiatm.service;

import com.example.masaiatm.dto.LoginDTO;
import com.example.masaiatm.dto.RegisterDTO;
import com.example.masaiatm.entity.Role;
import com.example.masaiatm.entity.User;
import com.example.masaiatm.exception.BadRequestException;
import com.example.masaiatm.repository.RoleRepository;
import com.example.masaiatm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(RegisterDTO registerDTO) {

        try {
            // add check for username exists in a DB
            if(userRepository.existsByUsername(registerDTO.getUsername())){
                throw new BadRequestException("Username is already taken!");
            }

            // add check for email exists in DB
            if(userRepository.existsByEmail(registerDTO.getEmail())){
                throw new BadRequestException("Email is already taken!");
            }

            // create user object
            User user = new User();
            user.setFirstName(registerDTO.getFirstName());
            user.setLastName(registerDTO.getLastName());
            user.setUserName(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            Role roles = roleRepository.findByName("ROLE_ADMIN").get();
            user.setRoles(Collections.singleton(roles));

            userRepository.save(user);
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return true;
    }

    public boolean loginUser(LoginDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (Exception e){
            e.getStackTrace();
        }

        return true;
    }
}
