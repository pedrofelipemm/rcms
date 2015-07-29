package br.ufscar.rcms.scorecard.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.rcms.scorecard.rest.Wrapper;
import br.ufscar.rcms.scorecard.rest.dto.AmountProducaoByResearcherDTO;
import br.ufscar.rcms.scorecard.rest.dto.AmountProducaoByYearDTO;
import br.ufscar.rcms.scorecard.service.ProducaoService;

@RestController
@RequestMapping("/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoService producaoService;

    @RequestMapping(value = "amount/year", produces = MediaType.APPLICATION_JSON_VALUE)
    public Wrapper<AmountProducaoByYearDTO> findProducoesByYear() {

        List<AmountProducaoByYearDTO> producoes = producaoService.findAmountProducaoByYear();
        Integer size = producoes.stream().map(AmountProducaoByYearDTO::getAmount).reduce((sum, amount) -> sum + amount).get();

        return new Wrapper<AmountProducaoByYearDTO>(producoes.size(), size, producoes);
    }

    @RequestMapping(value = "amount/researcher", produces = MediaType.APPLICATION_JSON_VALUE)
    public Wrapper<AmountProducaoByResearcherDTO> findAmountProducaoByResearcher() {

        List<AmountProducaoByResearcherDTO> producoes = producaoService.findAmountProducaoByResearcher();
        Integer size = producoes.stream().map(AmountProducaoByResearcherDTO::getAmount).reduce((sum, amount) -> sum + amount).get();

        return new Wrapper<AmountProducaoByResearcherDTO>(producoes.size(), size, producoes);
    }

}