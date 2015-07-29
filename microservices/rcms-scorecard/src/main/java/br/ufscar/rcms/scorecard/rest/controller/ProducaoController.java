package br.ufscar.rcms.scorecard.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.rcms.scorecard.rest.Wrapper;
import br.ufscar.rcms.scorecard.rest.dto.AmountProducaoByYearDTO;
import br.ufscar.rcms.scorecard.service.ProducaoService;

@RestController
@RequestMapping("/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoService producaoService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Wrapper<AmountProducaoByYearDTO> getProducoesByYeay() {

        List<AmountProducaoByYearDTO> producoes = producaoService.findAll();
        return new Wrapper<AmountProducaoByYearDTO>(producoes.size(), producoes);
    }

//    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Wrapper<AmountProducaoPesquisadorDTO> getProducoesByPesquisador() {
//        List<Producao> producoes = producaoService.findAll();
//        return new Wrapper<Producao>(producoes.size(), producoes);
//    }
}