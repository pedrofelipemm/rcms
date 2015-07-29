package br.ufscar.rcms.scorecard.rest.dto;

public class AmountProducaoByYearDTO extends AmountProducaoDTO {

    private static final long serialVersionUID = -5970482782225021574L;

    private final int year;

    public AmountProducaoByYearDTO(final int amount, final int year) {
        super(amount);
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}