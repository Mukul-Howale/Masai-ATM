package com.example.masaiatm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    
    private String gender;
    private int mobileNumber;
    private int age;
    private Date dateOfBirth;
}
