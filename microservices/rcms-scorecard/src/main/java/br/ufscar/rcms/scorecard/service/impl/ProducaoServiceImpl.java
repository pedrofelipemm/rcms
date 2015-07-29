package br.ufscar.rcms.scorecard.service.impl;

import static br.ufscar.rcms.scorecard.util.MiscellanyUtil.isEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.scorecard.model.entity.CitacaoBibliografica;
import br.ufscar.rcms.scorecard.model.entity.Producao;
import br.ufscar.rcms.scorecard.repository.ProducaoRepository;
import br.ufscar.rcms.scorecard.rest.dto.AmountProducaoByResearcherDTO;
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
    public List<AmountProducaoByYearDTO> findAmountProducaoByYear() {

        List<Producao> producoes = producaoRepository.findAll();
        Map<Integer, Long> collect = producoes.stream().collect(Collectors.groupingBy(p -> p.getAno(), Collectors.counting()));
        return collect.entrySet().stream().map(e -> new AmountProducaoByYearDTO(e.getValue().intValue(), e.getKey())).collect(Collectors.toList());
    }

    @Override
    public List<AmountProducaoByResearcherDTO> findAmountProducaoByResearcher() {

        List<Producao> producoes = producaoRepository.findAll();

        // TODO PEDRO TRY TO USE LAMBDA
        List<AmountProducaoByResearcherDTO> result = new ArrayList<AmountProducaoByResearcherDTO>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Producao producao : producoes) {
            List<CitacaoBibliografica> citacoes = producao.getCitacaoBibliograficas();
            for (CitacaoBibliografica citacao : citacoes) {
                if (!isEmpty(citacao.getPesquisador())) {
                    String nomeCitacao = citacao.getPesquisador().getNome();
                    Integer amount = map.get(nomeCitacao);
                    if (isEmpty(amount)) {
                        map.put(nomeCitacao, 1);
                    } else {
                        map.put(nomeCitacao, ++amount);
                    }
                }
            }
        }
        for (Entry<String, Integer> entry : map.entrySet()) {
            result.add(new AmountProducaoByResearcherDTO(entry.getValue(), entry.getKey()));
        }
        // TODO PEDRO TRY TO USE LAMBDA
        return result;
    }

}