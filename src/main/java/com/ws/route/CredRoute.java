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
                //.process("userCreateProcessor")
                .log("[POST /cred/create] Response Body: ${body}");


        from("direct:token-verify")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws JWTCreationException {
                        /*
                        Body body = exchange.getIn().getBody(Body.class);
                        String token = body.getToken();
                        DecodedJWT jwt = tokenManager.verify(token);
                        exchange.getIn().setBody(jwt);
                        System.out.println(jwt.getIssuedAt());
                        */
                    }
                })
                .log("TOKEN VERIFIED.");
    }

}
