package com.spring.kafka.api;

import com.spring.kafka.dto.PartitionMessageDto;
import com.spring.kafka.producer.MessageProducer;
import com.spring.kafka.vo.Farewell;
import com.spring.kafka.vo.Greeting;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final MessageProducer messageProducer;

    public ProducerController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/message")
    public String message(@RequestBody String message) {
        messageProducer.sendMessage(message);
        return "Sent Message Request !";
    }

    @PostMapping("/filtered-message")
    public String filteredMessage(@RequestBody String message) {
        messageProducer.sendFilteredMessage(message);
        return "Sent Filtered Message Request !";
    }

    @PostMapping("/partitioned-message")
    public String partitionedMessage(@RequestBody PartitionMessageDto dto) {
        messageProducer.sendPartitionedMessage(dto.getMessage(), dto.getPartition());
        return "Sent Partitioned Message Request !";
    }

    @PostMapping("/greeting")
    public String greeting(@RequestBody Greeting greeting) {
        messageProducer.sendGreeting(greeting);
        return "Sent Greeting Request !";
    }

    @PostMapping("/multitype/greeting")
    public String multitypeGreeting(@RequestBody Greeting greeting) {
        messageProducer.sendMultiType(greeting);
        return "Sent Multi Type[Greeting] Request !";
    }

    @PostMapping("/multitype/farewell")
    public String multitypeFarewell(@RequestBody Farewell farewell) {
        messageProducer.sendMultiType(farewell);
        return "Sent Multi Type[Farewell] Request !";
    }

    @PostMapping("/multitype/unknown")
    public String multitypeString(@RequestBody String unknown) {
        messageProducer.sendMultiType(unknown);
        return "Sent Multi Type[Unknown] Request !";
    }
}