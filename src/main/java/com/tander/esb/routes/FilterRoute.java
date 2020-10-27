package com.tander.esb.routes;

import com.tander.esb.exceptions.IllegalFileFormatException;
import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.IllegalFormatException;

@Component
public class FilterRoute extends RouteBuilder {
    @Autowired
    CamelContext camelContext;

    @Override
    public void configure() {

        camelContext.setStreamCaching(true);

        from("file://data/inbox")
                .streamCaching()
                .process("prepareToSendMail")
                .log(LoggingLevel.INFO, "sending email with count messages")
//                .to("smtp://mail.tander.ru?username=kovalenko-a&amp;password=..");
                .to("smtp://smtp.mail.ru:465?username=kovalenko_a_a@bk.ru&password=chiconyr310")
                .end()
                .choice()
                .when()
                .simple("${header.CamelFileName} ends with '.xml'")
                .log(LoggingLevel.INFO, "retreive xml message")
                .to("jms:queue:xml")
                .when()
                .simple("${header.CamelFileName} ends with '.txt'")
                .log(LoggingLevel.INFO, "retreive txt message")
                .to("jms:queue:txt")
                .otherwise()
                .to("jms:queue:other")
//                .throwException(IllegalFileFormatException.class, "Illegal format of message")
                .log(LoggingLevel.WARN, "Error format");

    }
}
