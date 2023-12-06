package com.news.domain.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String oldPassword;
    private String password;
}
