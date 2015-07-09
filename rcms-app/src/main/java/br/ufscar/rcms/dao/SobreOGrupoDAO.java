package br.ufscar.rcms.dao;

import br.ufscar.rcms.modelo.entidades.SobreOGrupo;

public interface SobreOGrupoDAO extends BaseDAO<SobreOGrupo, Long> {

    SobreOGrupo buscarPorIdioma(String idioma);

}
