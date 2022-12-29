package io.github.tlobato.mscreditappraiser.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ApprovedCard {
    private String card;
    private String flag;
    private BigDecimal basicLimit;
}