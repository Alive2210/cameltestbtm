package com.tander.esb.routes;

import com.tander.esb.processor.Proc;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpRoute extends RouteBuilder {


    @Autowired
    CamelContext camelContext;


    @Override
    public void configure() {
        camelContext.setStreamCaching(true);
        camelContext.getPropertiesComponent().addLocation("");
        ProducerTemplate template = camelContext.createProducerTemplate();


        Exchange send = template.send("https://www.google.com/webhp",
                exchange -> exchange.getIn()
                        .setHeader(Exchange.HTTP_QUERY, constant("hl=en&q=activemq")));
        Message out = send.getOut();
        int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        System.out.println(responseCode);

    /*    from("direct:start")
                .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
                .to("http://www.google.com")
                .process(new Proc())
                .to("mock:results");
*/
    }
}
