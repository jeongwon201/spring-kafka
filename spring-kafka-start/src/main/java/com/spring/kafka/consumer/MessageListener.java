package com.spring.kafka.consumer;

import com.spring.kafka.vo.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @KafkaListener(topics = "${topic.message.name}", groupId = "message", containerFactory = "messageKafkaListenerContainerFactory")
    public void listenMessage(String message) {
        System.out.println("Listen Message: " + message);
    }

    @KafkaListener(topics = "${topic.filtered-message.name}", groupId = "filter", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenFilteredMessage(String message) {
        System.out.println("Listen Filtered Message: " + message);
    }

    @KafkaListener(topics = "${topic.partitioned-message.name}", groupId = "header", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println("Listen With Headers: " + message + " from partition: " + partition);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${topic.partitioned-message.name}", partitions = {"0", "3"}), containerFactory = "partitionsKafkaListenerContainerFactory")
    public void listenToPartition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println("Listen to Partition: " + message + " from partition: " + partition);
    }

    @KafkaListener(topics = "${topic.greeting.name}", containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        System.out.println("Listen Greeting: " + greeting.toString());
    }
}