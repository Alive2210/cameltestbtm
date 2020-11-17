package com.tander.esb.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Proc implements Processor {
    @Override
    public void process(Exchange exchange) {
        Map<String, Object> headers = exchange.getOut().getHeaders();
        System.out.println(headers.toString());
    }
}
