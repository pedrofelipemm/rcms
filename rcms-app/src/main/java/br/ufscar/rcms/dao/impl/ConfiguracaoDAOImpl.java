package br.ufscar.rcms.dao.impl;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.ConfiguracaoDAO;
import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;

@Repository
public class ConfiguracaoDAOImpl extends BaseDAOImpl<Configuracao, Long>implements ConfiguracaoDAO {

    private static final long serialVersionUID = 32735639850047914L;

    public ConfiguracaoDAOImpl() {
        setClazz(Configuracao.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Configuracao> buscarPorTipo(final Tipo... tipos) {

        if (isEmpty(tipos)) {
            throw new IllegalArgumentException("tipo must not be null.");
        }

        return createQuery("select c from Configuracao c where c.key in :tipo").setParameter("tipo", Arrays.asList(tipos)).getResultList();
    }

    @Override
    public ConfiguracaoIndice buscarPorIdETipo(Long id, Tipo tipo) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM ConfiguracaoIndice c ");
        jpql.append("WHERE c.key = :tipo AND c.id = :id");

        Query query = createQuery(jpql.toString());
        query.setParameter("tipo", tipo);
        query.setParameter("id", id);

        return (ConfiguracaoIndice) query.getSingleResult();
    }
}