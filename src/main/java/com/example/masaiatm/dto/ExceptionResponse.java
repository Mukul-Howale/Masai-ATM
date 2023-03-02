package com.example.masaiatm.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date date;
    private String message;
    private String details;
}
