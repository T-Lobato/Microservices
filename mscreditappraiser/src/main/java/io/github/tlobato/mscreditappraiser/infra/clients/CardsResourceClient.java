package io.github.tlobato.mscreditappraiser.infra.clients;

import io.github.tlobato.mscreditappraiser.domain.model.Card;
import io.github.tlobato.mscreditappraiser.domain.model.ClientCard;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeUpTo(@RequestParam("income") Long income);

}