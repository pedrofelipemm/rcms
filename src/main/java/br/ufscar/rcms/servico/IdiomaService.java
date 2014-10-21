package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Idioma;

public interface IdiomaService extends Serializable {

    void salvar(Idioma idioma);

    void alterar(Idioma idioma);

    List<Idioma> buscarTodas();

    Idioma buscarPorDescricao(String Descricao);
}
