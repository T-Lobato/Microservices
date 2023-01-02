package io.github.tlobato.mscards.domain;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CardIssuanceRequestData {
    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal releasedLimit;
}