package br.ufscar.rcms.dao.impl;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.EnderecoDAO;
import br.ufscar.rcms.modelo.entidades.Endereco;

@Repository
public class EnderecoDAOImpl extends BaseDAOImpl<Endereco, Long> implements EnderecoDAO {

    private static final long serialVersionUID = 4493458867776635947L;

    public EnderecoDAOImpl() {

        setClazz(Endereco.class);
    }
}