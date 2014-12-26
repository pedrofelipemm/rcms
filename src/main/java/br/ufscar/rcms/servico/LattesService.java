package br.ufscar.rcms.servico;

import java.io.IOException;
import java.io.Serializable;

public interface LattesService extends Serializable {

    void executarComandoLattes(String nome, String hash) throws IOException;
}
