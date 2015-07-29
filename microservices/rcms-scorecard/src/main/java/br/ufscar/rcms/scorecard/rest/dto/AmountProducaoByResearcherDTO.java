package br.ufscar.rcms.scorecard.rest.dto;

public class AmountProducaoByResearcherDTO extends AmountProducaoDTO {

    private static final long serialVersionUID = 3777866764362458678L;

    private final String name;

    public AmountProducaoByResearcherDTO(final Integer amount, final String name) {
        super(amount);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}