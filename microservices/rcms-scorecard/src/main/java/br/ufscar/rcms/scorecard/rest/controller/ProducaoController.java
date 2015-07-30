package br.ufscar.rcms.scorecard.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.rcms.scorecard.commons.rest.Wrapper;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByResearcherDTO;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByYearDTO;
import br.ufscar.rcms.scorecard.service.ProducaoService;

@RestController
@RequestMapping("/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoService producaoService;

    @RequestMapping(value = "amount/year", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper<AmountProducaoByYearDTO>> findProducoesByYear() {

        List<AmountProducaoByYearDTO> producoes = producaoService.findAmountProducaoByYear();
        Integer size = producoes.stream().map(AmountProducaoByYearDTO::getAmount).reduce((sum, amount) -> sum + amount).get();

        Wrapper<AmountProducaoByYearDTO> wrapper = new Wrapper<AmountProducaoByYearDTO>(producoes.size(), size, producoes);

        return new ResponseEntity<Wrapper<AmountProducaoByYearDTO>>(wrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "amount/researcher", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper<AmountProducaoByResearcherDTO>> findAmountProducaoByResearcher() {

        List<AmountProducaoByResearcherDTO> producoes = producaoService.findAmountProducaoByResearcher();
        Integer size = producoes.stream().map(AmountProducaoByResearcherDTO::getAmount).reduce((sum, amount) -> sum + amount).get();

        Wrapper<AmountProducaoByResearcherDTO> wrapper = new Wrapper<AmountProducaoByResearcherDTO>(producoes.size(), size, producoes);

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

}