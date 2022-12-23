package io.github.tlobato.mscards.application.representation;

import io.github.tlobato.mscards.domain.ClientCard;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {
    private String name;
    private String flag;
    private BigDecimal basicLimit;

    public static CardsByClientResponse fromModel(ClientCard clientCard){
        return new CardsByClientResponse(
                clientCard.getCard().getName(),
                clientCard.getCard().getFlag().toString(),
                clientCard.getBasicLimit()
        );
    }
}