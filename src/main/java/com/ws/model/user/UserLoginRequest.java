package com.ws.model.user;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String mail;
    private String password;
}
