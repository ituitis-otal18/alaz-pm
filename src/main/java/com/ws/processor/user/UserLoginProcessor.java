package com.ws.processor.user;

import com.ws.model.user.*;
import com.ws.repo.UserRepo;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;

@Singleton
@Named("userLoginProcessor")
public class UserLoginProcessor implements Processor {

    @Inject
    private UserRepo userRepo;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {
        UserLoginRequest request = exchange.getIn().getBody(UserLoginRequest.class);
        String mail = request.getMail();

        UserEntity user = userRepo.findOneByMail(mail);

        if(user != null &&
                request.getPassword().equals(user.getPassword())){

            UserGenericResponse response = new UserGenericResponse();
            response.setMail(mail);

            exchange.getIn().setBody(response);
            exchange.setProperty("mail", mail);
            exchange.setProperty("code", "123789");
        }
        else throw new InstanceNotFoundException();
    }
}
