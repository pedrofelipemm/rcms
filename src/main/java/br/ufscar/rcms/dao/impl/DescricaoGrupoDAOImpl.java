package br.ufscar.rcms.dao.impl;

import br.ufscar.rcms.dao.DescricaoGrupoDAO;
import br.ufscar.rcms.modelo.entidades.DescricaoGrupo;

public class DescricaoGrupoDAOImpl extends BaseDAOImpl<DescricaoGrupo, Long> implements DescricaoGrupoDAO {

    private static final long serialVersionUID = 1L;

    public DescricaoGrupoDAOImpl() {

        setClazz(DescricaoGrupo.class);
    }
}
