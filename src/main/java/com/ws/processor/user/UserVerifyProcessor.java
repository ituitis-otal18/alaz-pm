package com.ws.processor.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.model.user.UserEntity;
import com.ws.model.user.UserVerifyRequest;
import com.ws.util.TokenManager;
import com.ws.repo.UserRepo;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;

@Singleton
@Named("userVerifyProcessor")
public class UserVerifyProcessor implements Processor {

    @Inject
    private UserRepo userRepo;

    @Inject
    TokenManager tokenManager;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {
        UserVerifyRequest request = exchange.getIn().getBody(UserVerifyRequest.class);
        String mail = request.getMail();

        UserEntity user = userRepo.findOneByMail(mail);

        if(user != null &&
                request.getCode().equals("123789") &&
                request.getPassword().equals(user.getPassword())){

            HashMap<String, String> payload = new ObjectMapper().convertValue(request, HashMap.class);
            String token = tokenManager.create(payload);
            exchange.getIn().setBody(token);
        }
        else throw new InstanceNotFoundException();
    }
}
