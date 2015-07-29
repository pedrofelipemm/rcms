package br.ufscar.rcms.scorecard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.scorecard.model.entity.Producao;
import br.ufscar.rcms.scorecard.repository.ProducaoRepository;
import br.ufscar.rcms.scorecard.rest.dto.AmountProducaoByYearDTO;
import br.ufscar.rcms.scorecard.service.ProducaoService;

@Service
@Transactional
public class ProducaoServiceImpl implements ProducaoService {

    @Autowired
    private ProducaoRepository producaoRepository;

//    Function<Entry<Integer, Long>, AmountProducaoByYearDTO> toAmountProducaoByYearDTO = new Function<Entry<Integer, Long>, AmountProducaoByYearDTO>() {
//        @Override
//        public AmountProducaoByYearDTO apply(final Entry<Integer, Long> entry) {
//            return new AmountProducaoByYearDTO(entry.getKey(), entry.getValue().intValue());
//        }
//    };

    @Override
    public List<AmountProducaoByYearDTO> findAll() {

        List<Producao> producoes = producaoRepository.findAll();
        Map<Integer, Long> collect = producoes.stream().collect(Collectors.groupingBy(p -> p.getAno(), Collectors.counting()));
        return collect.entrySet().stream().map(e -> new AmountProducaoByYearDTO(e.getKey(), e.getValue().intValue())).collect(Collectors.toList());
    }

    // @Override
    // public List<AmountProducaoByYearDTO> findAll() {
    // int i = 0;
    // List<Producao> producoes = producaoRepository.findAll();
    // for (Producao producao : producoes) {
    // List<CitacaoBibliografica> citacoes = producao.getCitacaoBibliograficas();
    // for (CitacaoBibliografica citacao : citacoes) {
    // citacao.getPesquisador();
    // System.out.println(++i);
    // }
    // }
    // return producoes;
    // }

}