package br.ufscar.rcms.scorecard.rest.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AmountProducaoDTO implements Serializable {

    private final int amount;

    public AmountProducaoDTO(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}