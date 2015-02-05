package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.FormacaoAcademicaDAO;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;

@Repository
public class FormacaoAcademicaDAOImpl extends BaseDAOImpl<FormacaoAcademica, Integer> implements FormacaoAcademicaDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public List<FormacaoAcademica> buscarFormacaoAcademica(Long idUsuario) {

        Query query = createQuery("select fa from " + FormacaoAcademica.class.getName()
                + " fa join fa.pesquisador p where p.idUsuario = :idUsuario");

        query.setParameter("idUsuario", idUsuario);

        return query.getResultList();
    }
}