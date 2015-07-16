package br.ufscar;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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