package com.news.domain.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String password;
    private String phoneNumber;
}
