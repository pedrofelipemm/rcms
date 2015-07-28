package br.ufscar.rcms.scorecard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.scorecard.model.entity.CitacaoBibliografica;
import br.ufscar.rcms.scorecard.model.entity.Producao;
import br.ufscar.rcms.scorecard.repository.ProducaoRepository;
import br.ufscar.rcms.scorecard.service.ProducaoService;

@Service
@Transactional
public class ProducaoServiceImpl implements ProducaoService {

    @Autowired
    private ProducaoRepository producaoRepository;

    @Override
    public List<Producao> findAll() {
        int i = 0;
        List<Producao> producoes = producaoRepository.findAll();
        for (Producao producao : producoes) {
            List<CitacaoBibliografica> citacoes = producao.getCitacaoBibliograficas();
            for (CitacaoBibliografica citacao : citacoes) {
                citacao.getPesquisador();
                i++;
            }
        }
        System.out.println(i);
        return producoes;
    }

}