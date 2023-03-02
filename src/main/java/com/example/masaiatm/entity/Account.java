package com.example.masaiatm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
    private String accountNo;
    private String ifsc;
    private int balance;
    private String bank;
    private Date accountOpen;

    @OneToOne
    User user = new User();
}
