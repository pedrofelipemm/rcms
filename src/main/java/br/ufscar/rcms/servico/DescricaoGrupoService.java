package br.ufscar.rcms.servico;

import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.DescricaoGrupo;

public interface DescricaoGrupoService extends Serializable {

    void salvar(DescricaoGrupo descricaoGrupo);

    void alterar(DescricaoGrupo descricaoGrupo);
}
