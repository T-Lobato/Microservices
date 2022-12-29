package io.github.tlobato.mscreditappraiser.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientEvaluationReturn {
    private List<ApprovedCard> cards;
}