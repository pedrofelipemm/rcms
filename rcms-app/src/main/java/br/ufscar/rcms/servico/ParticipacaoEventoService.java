package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.ParticipacaoEvento;

public interface ParticipacaoEventoService extends Serializable {

    void remover(List<ParticipacaoEvento> participacaoEventos);

    void remover(ParticipacaoEvento participacaoEvento);
}