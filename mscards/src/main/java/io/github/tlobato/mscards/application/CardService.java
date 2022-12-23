package io.github.tlobato.mscards.application;

import io.github.tlobato.mscards.domain.Card;
import io.github.tlobato.mscards.infra.repository.CardRepository;
import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public Card save(Card card){
        return cardRepository.save(card);
    }

    public List<Card> getCardIncomeLessEqual(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return cardRepository.findByIncomeLessThanEqual(incomeBigDecimal);
    }

}
