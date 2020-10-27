package com.tander.esb.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FromBrokerToOutboxDir extends RouteBuilder {
    @Override
    public void configure() {
        from("jms:xml")
                .log(LoggingLevel.INFO, "save message xml to dir")
                .to("file:data/outbox/xml");
        from("jms:other")
                .log(LoggingLevel.INFO, "save trash message to dir")
                .to("file:data/outbox/trash");
    }
}
