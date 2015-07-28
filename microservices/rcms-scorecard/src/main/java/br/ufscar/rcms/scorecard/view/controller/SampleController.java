package br.ufscar.rcms.scorecard.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.rcms.scorecard.model.entity.Producao;
import br.ufscar.rcms.scorecard.service.ProducaoService;

@RestController
@RequestMapping("/samples")
public class SampleController {

    @Autowired
    private ProducaoService producaoService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Producao> getSamples() {
        // List<Sample> samples = new ArrayList<Sample>();
        // for (int i = 0; i < 11; i++) {
        // samples.add(new Sample(i, "Sample " + i));
        // }
        // return samples;
        return producaoService.findAll();
    }
}