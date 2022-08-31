package com.ws.route;

import com.auth0.jwt.exceptions.JWTCreationException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CredRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .bindingMode(RestBindingMode.auto);

        rest("/cred")
                .post("/create")
                .consumes("application/json")
                .to("direct:cred-create");

        from("direct:cred-create")
                .log("[POST /cred/create] Request Body: ${body}")
                .process("tokenVerifier")
                .log("[POST /cred/create] Token verified.")
                //.process("credCreateProcessor")
                .log("[POST /cred/create] Response Body: ${body}");
    }

}
