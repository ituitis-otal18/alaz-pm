package com.ws.processor.credential;

import com.ws.repo.CredRepo;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("credDeleteProcessor")
public class CredDeleteProcessor implements Processor {

    @Inject
    private CredRepo credRepo;

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}
