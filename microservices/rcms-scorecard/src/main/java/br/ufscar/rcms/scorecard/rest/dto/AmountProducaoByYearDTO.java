package br.ufscar.rcms.scorecard.rest.dto;

public class AmountProducaoByYearDTO extends AmountProducaoDTO {

    private static final long serialVersionUID = -5970482782225021574L;

    private final Integer year;

    public AmountProducaoByYearDTO(final Integer amount, final Integer year) {
        super(amount);
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }
}