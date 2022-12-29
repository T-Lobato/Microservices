package io.github.tlobato.mscreditappraiser.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ClientCard {

    private String name;
    private String flag;
    private BigDecimal basicLimit;
}