package com.example.hwthree.usr.dto;

import lombok.Data;

@Data
public class UsrUserChangePasswordDto {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
