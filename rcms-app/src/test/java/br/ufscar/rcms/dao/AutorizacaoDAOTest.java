package br.ufscar.rcms.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.modelo.entidades.Autorizacao;

public class AutorizacaoDAOTest extends AbstractDAOTestBase {

    @Autowired
    private AutorizacaoDAO autorizacaoDAO;

    @Before
    public void setUp() {}

    @Test
    public void findByRoleTest() {

        Autorizacao admin = autorizacaoDAO.findByRole("ROLE_ADMIN");

        assertEquals("ROLE_ADMIN", admin.getNomeAutorizacao());
        assertEquals("Administrador", admin.getDescricao());
    }
}