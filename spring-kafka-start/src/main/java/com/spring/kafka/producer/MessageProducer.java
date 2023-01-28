package com.spring.kafka.producer;

import com.spring.kafka.vo.Greeting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MessageProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

    private KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    @Value("${topic.message.name}")
    String messageTopicName;

    @Value("${topic.filtered-message.name}")
    String filteredMessageTopicName;

    @Value("${topic.partitioned-message.name}")
    String partitionedMessageTopicName;

    @Value("${topic.greeting.name}")
    String greetingTopicName;

    @Value("${topic.multi-type.name}")
    String multiTypeTopicName;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Greeting> greetingKafkaTemplate, KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.greetingKafkaTemplate = greetingKafkaTemplate;
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(messageTopicName, message);
        future.whenComplete((result, e) -> {
            if (e == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + message + "] due to : " + e.getMessage());
            }
        });
    }

    public void sendFilteredMessage(String message) {
        kafkaTemplate.send(filteredMessageTopicName, message)
                .whenComplete((result, e) -> {
                    if (e == null) {
                        System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                    } else {
                        System.out.println("Unable to send message=[" + message + "] due to : " + e.getMessage());
                    }
                });
    }

    public void sendPartitionedMessage(String message, int partition) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(partitionedMessageTopicName, partition, null, message);
        future.whenComplete((result, e) -> {
            if (e == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + message + "] due to : " + e.getMessage());
            }
        });
    }

    public void sendGreeting(Greeting greeting) {
        CompletableFuture<SendResult<String, Greeting>> future = greetingKafkaTemplate.send(greetingTopicName, greeting);
        future.whenComplete((result, e) -> {
            if (e == null) {
                System.out.println("Sent greeting object with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send greeting object due to : " + e.getMessage());
            }
        });
    }

    public void sendMultiType(Object obj) {
        CompletableFuture<SendResult<String, Object>> future = multiTypeKafkaTemplate.send(multiTypeTopicName, obj);
        future.whenComplete((result, e) -> {
            if (e == null) {
                System.out.println("Sent object[" + obj.getClass().getSimpleName() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send object[" + obj.getClass().getSimpleName() + "] due to : " + e.getMessage());
            }
        });
    }
}