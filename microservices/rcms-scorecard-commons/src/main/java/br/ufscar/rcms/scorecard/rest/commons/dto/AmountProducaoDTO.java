package br.ufscar.rcms.scorecard.rest.commons.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AmountProducaoDTO implements Serializable {

    private final Integer amount;

    public AmountProducaoDTO(final Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}