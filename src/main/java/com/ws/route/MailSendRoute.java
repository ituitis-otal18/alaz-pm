package com.ws.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class MailSendRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:mail-send")
                .doTry()
                .transform().simple("<h3>CODE: ${exchangeProperty.code}</h3>")
                .setHeader("to", simple("${exchangeProperty.mail}"))
                .setHeader("subject", simple("Verification"))
                .setHeader("contentType", simple("text/html"))
                .toD("smtps://smtp.gmail.com:465?username={{smtp.username}}&password={{smtp.password}}")
                .log("Verification code '${exchangeProperty.code}' sent to '${exchangeProperty.mail}'");
    }

}
