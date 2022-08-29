package com.ws.processor.user;

import com.ws.model.user.UserCreateRequest;
import com.ws.model.user.UserGenericResponse;
import com.ws.model.user.UserEntity;
import com.ws.repo.UserRepo;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("userCreateProcessor")
public class UserCreateProcessor implements Processor {

    @Inject
    private UserRepo userRepo;

    @Override
    public void process(Exchange exchange) throws Exception {
        UserCreateRequest body = exchange.getIn().getBody(UserCreateRequest.class);

        UserEntity user = new UserEntity();
        user.setMail(body.getMail());
        user.setPassword(body.getPassword());
        userRepo.save(user);

        UserGenericResponse response = new UserGenericResponse();
        response.setMail(body.getMail());
        exchange.getIn().setBody(response);
    }
}
