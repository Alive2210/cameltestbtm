package com.tander.esb.processor;

import org.apache.camel.Exchange;

import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class PrepareToSendMail implements Processor {
@Override
    public void process(Exchange exchange) {
        long countTxt = 0;
        long countXml = 0;
        long countOther = 0;

        System.out.println(exchange.getIn().getHeader("CamelFileName").toString());
        if (exchange.getIn().getHeader("CamelFileName").toString().endsWith(".xml")) {
            countXml++;
        }
        if (exchange.getIn().getHeader("CamelFileName").toString().endsWith(".txt")) {
            countTxt++;
        } if (!(exchange.getIn().getHeader("CamelFileName").toString().endsWith(".xml"))
                &&(!(exchange.getIn().getHeader("CamelFileName").toString().endsWith(".txt")))) {
            countOther++;
        }
        long count = countOther + countTxt + countXml;
        if (count % 2 == 0) {
            String body = new StringBuilder()
                    .append("countTxt:")
                    .append(countTxt)
                    .append("\n")
                    .append("countXml:")
                    .append(countXml)
                    .append("\n")
                    .append("countOther:")
                    .append(countOther)
                    .append("\n")
                    .append("and all count:")
                    .append(count)
                    .toString();

            exchange.getOut().setHeader("to", "kovalenko_a_a@bk.ru");
//            exchange.getOut().setHeader("to", "kovalenko-a@magnit.ru");
            exchange.getOut().setHeader("From", "toxachik@gmail.com");
            exchange.getOut().setHeader("Subject", "Count files from dir");
            exchange.getOut().setBody(body);
        }
    }
}

