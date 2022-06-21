package com.example.springrestcontroller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePasswordRequest {
    @NotEmpty(message = "Token cannot be null or empty")
    private String token;

    @NotEmpty(message = "New Password cannot be null or empty")
    @Size(min = 8, max = 50, message = "New Password must have at least 8 characters")
    @NotBlank(message = "New Password must not contain whitespace")
    private String newPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
