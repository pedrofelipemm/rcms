package br.ufscar.rcms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.ParticipacaoEventoDAO;
import br.ufscar.rcms.modelo.entidades.ParticipacaoEvento;

@Repository
public class ParticipacaoEventoDAOImpl extends BaseDAOImpl<ParticipacaoEvento, Long> implements ParticipacaoEventoDAO {

    private static final long serialVersionUID = 4493458867776635945L;

    @Override
    public void remover(List<ParticipacaoEvento> participacaoEventos) {
        for (ParticipacaoEvento participacaoEvento : participacaoEventos) {
            remover(participacaoEvento);
        }
    }
}