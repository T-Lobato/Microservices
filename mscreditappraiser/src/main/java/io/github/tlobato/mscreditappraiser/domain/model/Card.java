package io.github.tlobato.mscreditappraiser.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Card {

    private Long id;
    private String name;
    private String flag;
    private BigDecimal basicLimit;
}