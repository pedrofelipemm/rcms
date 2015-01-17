package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.GrandeAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;

@Repository
public class GrandeAreaAtuacaoDAOImpl extends BaseDAOImpl<GrandeAreaAtuacao, Long> implements GrandeAreaAtuacaoDAO {

	private static final long serialVersionUID = 3964043277807387830L;

	public GrandeAreaAtuacaoDAOImpl() {

        setClazz(GrandeAreaAtuacao.class);
    }

    @Override
    public List<GrandeAreaAtuacao> buscarPorDescricao(String Descricao) {
        Query q = getEntityManager().createQuery("SELECT a from GrandeAreaAtuacao AS a WHERE a.descricao Like '%:d%'");
        q.setParameter("d", Descricao);

        try {
        	Object lst = q.getResultList();
        	if (lst != null) {
                return (List<GrandeAreaAtuacao>)lst;
            } else {
                return null;
            }
        } catch (NoResultException noResultException) {
            return null;
        }
    }

}
