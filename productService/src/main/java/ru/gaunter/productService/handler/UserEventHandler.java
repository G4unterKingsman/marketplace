package ru.gaunter.productService.handler;


import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "product-created-event-topic")
public class UserEventHandler {
    @KafkaHandler
    public void handleEvent(){

    }
}
