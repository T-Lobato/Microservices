package io.github.tlobato.mscreditappraiser.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tlobato.mscreditappraiser.domain.model.CardIssuanceRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherCardIssuanceRequest {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssueCard;

    public void cardRequest(CardIssuanceRequestData data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueIssueCard.getName(), json);
    }

    private String convertIntoJson(CardIssuanceRequestData data) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}