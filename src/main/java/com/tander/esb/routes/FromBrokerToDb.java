package com.tander.esb.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FromBrokerToDb extends RouteBuilder {
    @Override
    public void configure() {
//        onException(IOException.class)
        from("jms:txt")
                .transacted()
                .multicast()
                .parallelProcessing()
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, ">>> save message to db ${body}")
//                .to("file:data/outbox/txt", "sql:insert into jms (body) values (:#${body})")
                .log(LoggingLevel.INFO, "saved message")
                .end();
    }
}

