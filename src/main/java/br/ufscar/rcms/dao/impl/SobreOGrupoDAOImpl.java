package br.ufscar.rcms.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.SobreOGrupoDAO;
import br.ufscar.rcms.modelo.entidades.SobreOGrupo;

@Repository
public class SobreOGrupoDAOImpl extends BaseDAOImpl<SobreOGrupo, Long> implements SobreOGrupoDAO {

    private static final long serialVersionUID = 3874665354979246807L;

    @Override
    public SobreOGrupo buscarPorIdioma(String idioma) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("select g from " + SobreOGrupo.class.getName() + " g ");
        jpql.append("where g.idioma = :idioma ");

        Query query = createQuery(jpql.toString());
        query.setParameter("idioma", idioma);

        try {
            return (SobreOGrupo) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
    }

}
