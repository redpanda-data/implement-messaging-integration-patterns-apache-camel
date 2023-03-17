package com.example.apachecamelwithredpanda;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRedpandaRouter extends RouteBuilder {

    @Value("${camel.component.kafka.brokers}")
    private String kafka_server;

    @Override
    public void configure() throws Exception {
        System.out.println("kafka_server is " + kafka_server);
        // Kafka consumer
        from("kafka:user-login?brokers=" + kafka_server)
                .log("Message received from Kafka : ${body}")
                .bean(UserLoginProcessor.class, "process(${body})")
                .errorHandler(deadLetterChannel("kafka:user-login-DLT?brokers=" + kafka_server).useOriginalMessage());

        // Kafka Consumer
        // from("kafka:myTopic?brokers="+kafka_server)
        // .log("Message received from Kafka : ${body}")
        // .log(" on the topic ${headers[kafka.TOPIC]}")
        // .log(" on the partition ${headers[kafka.PARTITION]}")
        // .log(" with the offset ${headers[kafka.OFFSET]}")
        // .log(" with the key ${headers[kafka.KEY]}");

    }

}
