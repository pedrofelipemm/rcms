package br.ufscar.rcms.dao.impl;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.DescricaoGrupoDAO;
import br.ufscar.rcms.modelo.entidades.DescricaoGrupo;

@Repository
public class DescricaoGrupoDAOImpl extends BaseDAOImpl<DescricaoGrupo, Long> implements DescricaoGrupoDAO {

    private static final long serialVersionUID = 1L;

    public DescricaoGrupoDAOImpl() {

        setClazz(DescricaoGrupo.class);
    }
}
