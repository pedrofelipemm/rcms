package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.ParticipacaoEventoDAO;
import br.ufscar.rcms.modelo.entidades.ParticipacaoEvento;
import br.ufscar.rcms.servico.ParticipacaoEventoService;

@Service("participacaoEventoService")
@Transactional
public class ParticipacaoEventoServiceImpl implements ParticipacaoEventoService {

    private static final long serialVersionUID = 2622970170958920009L;

    @Autowired
    private ParticipacaoEventoDAO participacaoEventoDAO;

    @Override
    public void remover(List<ParticipacaoEvento> participacaoEventos) {
        participacaoEventoDAO.remover(participacaoEventos);
    }

    @Override
    public void remover(ParticipacaoEvento participacaoEvento) {
        participacaoEventoDAO.remover(participacaoEvento);
    }
}