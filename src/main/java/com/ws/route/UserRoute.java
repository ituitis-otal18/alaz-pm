package com.ws.route;

import com.ws.model.user.UserCreateRequest;
import com.ws.model.user.UserGenericResponse;
import com.ws.model.user.UserLoginRequest;
import com.ws.model.user.UserVerifyRequest;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class UserRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .bindingMode(RestBindingMode.auto);

        rest("/user")
                .consumes("application/json")
                .post("/login")
                    .type(UserLoginRequest.class)
                    .outType(UserGenericResponse.class)
                    .to("direct:user-login")
                .post("/create")
                    .type(UserCreateRequest.class)
                    .outType(UserGenericResponse.class)
                    .to("direct:user-create")
                .post("/verify")
                    .type(UserVerifyRequest.class)
                    .outType(String.class)
                    .to("direct:user-verify");

        from("direct:user-create")
                .log("[POST /user/create] Request: ${body}")
                .process("userCreateProcessor")
                .log("[POST /user/create] Response: ${body}");

        from("direct:user-login")
                .log("[POST /user/login] Request: ${body}")
                .process("userLoginProcessor")
                .to("direct:mail-send")
                .log("[POST /user/login] Response: ${body}");

        from("direct:user-verify")
                .log("[POST /user/verify] Request: ${body}")
                .process("userVerifyProcessor")
                .log("[POST /user/verify] Response: ${body}");

    }

}