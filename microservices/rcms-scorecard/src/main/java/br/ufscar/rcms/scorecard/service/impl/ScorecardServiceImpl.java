package br.ufscar.rcms.scorecard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.scorecard.model.entity.ProducaoPesquisador;
import br.ufscar.rcms.scorecard.repository.ScorecardRepository;
import br.ufscar.rcms.scorecard.service.ScorecardService;

@Service
@Transactional
public class ScorecardServiceImpl implements ScorecardService {

    @Autowired
    private ScorecardRepository scorecardRepository;

    @Override
    public List<ProducaoPesquisador> findAll() {
        return scorecardRepository.findAll();
    }

}