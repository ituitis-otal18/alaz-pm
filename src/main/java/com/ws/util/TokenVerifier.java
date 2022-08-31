package com.ws.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("tokenVerifier")
public class TokenVerifier implements Processor {

    @Inject
    TokenManager tokenManager;

    @Override
    public void process(Exchange exchange) throws Exception {
        String auth = exchange.getIn().getHeader("Authorization", String.class);
        System.out.println("AUTH: "+auth);

        if (auth.startsWith("Bearer ")){
            String token = auth.substring(7);
            DecodedJWT jwt = tokenManager.verify(token);
            exchange.getIn().setBody(jwt);

            System.out.println(jwt.toString());
        }
    }
}
