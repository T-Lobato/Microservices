package io.github.tlobato.mscards.application;

import io.github.tlobato.mscards.application.representation.CardSaveRequest;
import io.github.tlobato.mscards.application.representation.CardsByClientResponse;
import io.github.tlobato.mscards.domain.Card;
import io.github.tlobato.mscards.domain.ClientCard;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService cardService;

    private final ClientCardService clientCardService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeUpTo(@RequestParam("income") Long income) {
        List<Card> list = cardService.getCardIncomeLessEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByClientResponse>> getCardsByClient(@RequestParam("cpf") String cpf) {
        List<ClientCard> list = clientCardService.listCardsByCpf(cpf);
        List<CardsByClientResponse> resultList = list.stream()
                .map(CardsByClientResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}