package io.github.tlobato.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQconfig {

    @Value("${mq.queues.issuance-cards}")
    private String issuanceQueueCards;

    @Bean
    public Queue queueIssueCard(){
        return new Queue(issuanceQueueCards, true);
    }
}