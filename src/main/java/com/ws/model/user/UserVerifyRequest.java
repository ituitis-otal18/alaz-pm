package com.ws.model.user;

import lombok.Data;

@Data
public class UserVerifyRequest {
    private String mail;
    private String password;
    private String code;
}
