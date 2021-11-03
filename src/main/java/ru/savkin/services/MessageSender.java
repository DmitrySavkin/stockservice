package ru.savkin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class MessageSender {

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @Autowired
    public QueueMessagingTemplate queueMessagingTemplate;

    public void send(@PathVariable(value = "message") String message) {
        Message payload =  MessageBuilder.withPayload(message)
                .setHeader("sender", "techno town techie")
                .build();

        queueMessagingTemplate.send(endPoint, payload);
    }
}
