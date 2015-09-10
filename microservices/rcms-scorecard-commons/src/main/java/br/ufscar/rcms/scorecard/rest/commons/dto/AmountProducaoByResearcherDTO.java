package br.ufscar.rcms.scorecard.rest.commons.dto;

public class AmountProducaoByResearcherDTO extends AmountProducaoDTO {

    private static final long serialVersionUID = 3777866764362458678L;

    private final String name;

    // TODO PEDRO DELETE
    public AmountProducaoByResearcherDTO(final Integer amount, final String name) {
        super(amount);
        this.name = name;
    }

    public AmountProducaoByResearcherDTO(final Long amount, final String name) {
        super(amount.intValue());
        this.name = name;
    }

    public String getName() {
        return name;
    }
}