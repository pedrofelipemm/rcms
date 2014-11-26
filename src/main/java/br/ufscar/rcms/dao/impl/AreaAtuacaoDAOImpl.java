package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.AreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;

@Repository
public class AreaAtuacaoDAOImpl extends BaseDAOImpl<GrandeAreaAtuacao, Long> implements AreaAtuacaoDAO {

    private static final long serialVersionUID = -4170408904792442435L;

    public AreaAtuacaoDAOImpl() {

        setClazz(GrandeAreaAtuacao.class);
    }

    @Override
    public List<GrandeAreaAtuacao> buscarPorDescricao(String Descricao) {
        Query q = getEntityManager().createQuery("SELECT a from GrandeAreaAtuacao AS a WHERE a.descricao Like '%:d%'");
        q.setParameter("d", Descricao);

        try {
        	Object lst = q.getResultList();
        	if (lst != null)
        		return (List<GrandeAreaAtuacao>)lst;
        	else
        		return null;
        } catch (NoResultException noResultException) {
            return null;
        }
    }
}
