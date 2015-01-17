package br.ufscar.rcms.servico;

import java.io.IOException;
import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public interface LattesService extends Serializable {

    void executarComandoLattes(Pesquisador pesquisador) throws IOException;
}
