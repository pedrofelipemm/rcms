package br.ufscar.rcms.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.AreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

@Repository
public class AreaAtuacaoDAOImpl extends BaseDAOImpl<AreaAtuacao, Long> implements AreaAtuacaoDAO {

    private static final long serialVersionUID = -4170408904792442435L;

    public AreaAtuacaoDAOImpl() {

        setClazz(AreaAtuacao.class);
    }

    @Override
    public AreaAtuacao buscarPorDescricao(String Descricao) {
        Query q = getEntityManager().createQuery("SELECT a from AreaAtuacao AS a WHERE a.descricao = :d");
        q.setParameter("d", Descricao);

        try {
            return (AreaAtuacao) q.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }
}
