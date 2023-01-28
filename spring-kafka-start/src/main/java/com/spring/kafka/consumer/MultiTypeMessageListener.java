package com.spring.kafka.consumer;

import com.spring.kafka.vo.Farewell;
import com.spring.kafka.vo.Greeting;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multiGroup", topics = "${topic.multi-type.name}")
public class MultiTypeMessageListener {

    @KafkaHandler
    public void handleGreeting(Greeting greeting) {
        System.out.println("Listen Multi Type [Greeting]: " + greeting.toString());
    }

    @KafkaHandler
    public void handleFarewell(Farewell farewell) {
        System.out.println("Listen Multi Type [Farewell]: " + farewell.toString());
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Listen Multi Type [Unknown]: " + object.toString());
    }
}
