package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.ParticipacaoEvento;

public interface ParticipacaoEventoDAO extends BaseDAO<ParticipacaoEvento, Long> {

    public void remover(List<ParticipacaoEvento> participacaoEventos);
}