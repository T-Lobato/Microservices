package io.github.tlobato.mscards.application.representation;

import io.github.tlobato.mscards.domain.Card;
import io.github.tlobato.mscards.domain.CardFlag;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CardSaveRequest {
    private String name;
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card toModel(){
        return new Card(name, flag, income, basicLimit);
    }
}