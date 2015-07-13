package br.ufscar.rcms.servico;

import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.SobreOGrupo;

public interface SobreOGrupoService extends Serializable {

    SobreOGrupo buscarPorIdioma(String idioma);

    void salvar(SobreOGrupo sobreOGrupo);

    void alterar(SobreOGrupo sobreOGrupo);

}
