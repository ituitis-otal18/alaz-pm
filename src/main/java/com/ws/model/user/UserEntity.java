package com.ws.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mail;
    private String password;
}
