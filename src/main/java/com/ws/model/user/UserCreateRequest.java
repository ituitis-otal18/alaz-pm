package com.ws.model.user;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String mail;
    private String password;
}
