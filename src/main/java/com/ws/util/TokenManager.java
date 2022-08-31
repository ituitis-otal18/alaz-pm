package com.ws.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.vertx.core.cli.annotations.Name;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Named;
import javax.inject.Singleton;

import java.util.Map;

@Singleton
@Named("tokenManager")
public class TokenManager {

    Algorithm algorithm;
    JWTVerifier verifier;

    public TokenManager(@ConfigProperty(name = "token.secret") String secret) {
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
    }

    public String create(Map<String, String> payload) throws JWTCreationException {
        return JWT.create()
                .withIssuer("auth0")
                .withPayload(payload)
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) throws JWTVerificationException {
        return verifier.verify(token);
    }
}
