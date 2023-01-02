package io.github.tlobato.mscreditappraiser.application;

import feign.FeignException;
import io.github.tlobato.mscreditappraiser.application.ex.ClientsDataNotFoundException;
import io.github.tlobato.mscreditappraiser.application.ex.ErrorComunicationMicroservicesException;
import io.github.tlobato.mscreditappraiser.application.ex.ErrorRequestCardException;
import io.github.tlobato.mscreditappraiser.domain.model.ApprovedCard;
import io.github.tlobato.mscreditappraiser.domain.model.Card;
import io.github.tlobato.mscreditappraiser.domain.model.CardIssuanceRequestData;
import io.github.tlobato.mscreditappraiser.domain.model.CardRequestProtocol;
import io.github.tlobato.mscreditappraiser.domain.model.ClientCard;
import io.github.tlobato.mscreditappraiser.domain.model.ClientData;
import io.github.tlobato.mscreditappraiser.domain.model.ClientEvaluationReturn;
import io.github.tlobato.mscreditappraiser.domain.model.ClientStatus;
import io.github.tlobato.mscreditappraiser.infra.clients.CardsResourceClient;
import io.github.tlobato.mscreditappraiser.infra.clients.ClientResourceClient;
import io.github.tlobato.mscreditappraiser.infra.mqueue.PublisherCardIssuanceRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final ClientResourceClient client;

    private final CardsResourceClient cards;

    private final PublisherCardIssuanceRequest publisherCardIssuanceRequest;

    public ClientStatus getClientStatus(String cpf) throws ClientsDataNotFoundException, ErrorComunicationMicroservicesException {
        try {
            ResponseEntity<ClientData> clientDataResponse = client.clientsData(cpf);
            ResponseEntity<List<ClientCard>> cardsResponse = cards.getCardsByClient(cpf);

            return ClientStatus.builder()
                    .client(clientDataResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientsDataNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }

    public ClientEvaluationReturn doEvaluation(String cpf, Long income)
            throws ClientsDataNotFoundException, ErrorComunicationMicroservicesException {
        try {
            ResponseEntity<ClientData> clientDataResponse = client.clientsData(cpf);
            ResponseEntity<List<Card>> cardsResponse = cards.getCardsIncomeUpTo(income);

            List<Card> cardList = cardsResponse.getBody();
            var approvedCardList = cardList.stream().map(card -> {
                ClientData clientData = clientDataResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());
                var fator = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = fator.multiply(basicLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setFlag(card.getFlag());
                approvedCard.setBasicLimit(approvedLimit);

                return approvedCard;

            }).collect(Collectors.toList());

            return new ClientEvaluationReturn(approvedCardList);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientsDataNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }

    public CardRequestProtocol requestCardIssuance(CardIssuanceRequestData data){
        try{
            publisherCardIssuanceRequest.cardRequest(data);
            var protocol = UUID.randomUUID().toString();
            return new CardRequestProtocol(protocol);

        } catch (Exception e){
            throw new ErrorRequestCardException(e.getMessage());
        }
    }
}