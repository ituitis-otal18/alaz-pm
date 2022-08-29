package com.ws.model.credential;

import com.ws.model.user.UserEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Credentials")
public class CredentialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private UserEntity user;

    private String website;
    private String username;
    private String mail;
    private String password;
}
