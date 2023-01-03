package io.github.tlobato.mscards.infra.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tlobato.mscards.domain.Card;
import io.github.tlobato.mscards.domain.CardIssuanceRequestData;
import io.github.tlobato.mscards.domain.ClientCard;
import io.github.tlobato.mscards.infra.repository.CardRepository;
import io.github.tlobato.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssuanceCardsSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.issuance-cards}")
    public void receiveIssueRequest(@Payload String payload){

        var mapper = new ObjectMapper();
        try {
            CardIssuanceRequestData data = mapper.readValue(payload, CardIssuanceRequestData.class);
            Card card = cardRepository.findById(data.getIdCard()).orElseThrow();

            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(data.getCpf());
            clientCard.setBasicLimit(data.getReleasedLimit());

            clientCardRepository.save(clientCard);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}