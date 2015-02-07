package br.ufscar.rcms.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.factory.FormacaoAcademicaFactory;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class FormacaoAcadmicaDAOTest extends AbstractDAOTestBase {

    @Autowired
    private FormacaoAcademicaDAO formacaoAcademicaDAO;

    @Before
    public void init(){

        Pesquisador pesquisador = new PesquisadorBuilder(1L, "login", "nome", "senha", "codigoLattes", "email", true,
                "resumoProfissional").build();

        FormacaoAcademica formacaoAcademica = FormacaoAcademicaFactory.createFormacaoAcademica(1L, 2000, 2000,
                "descricao", "nomeInstituicao", "tipo", pesquisador);

        FormacaoAcademica formacaoAcademica2 = FormacaoAcademicaFactory.createFormacaoAcademica(2L, 2000, 2000,
                "descricao", "nomeInstituicao", "tipo", pesquisador);

        pesquisador.setFormacoes(Arrays.asList(formacaoAcademica, formacaoAcademica2));

        salvar(pesquisador, formacaoAcademica, formacaoAcademica2);
    }

    // TOOD PEDRO
    @Ignore
    @Test
    public void buscarFormacaoAcademicaPorPesquisadorTest() {

        List<FormacaoAcademica> formacoes = formacaoAcademicaDAO.buscarFormacaoAcademica(1L);
        assertEquals(2, formacoes.size());

        FormacaoAcademica item1 = formacoes.get(0);

        assertEquals(Integer.valueOf(2000), item1.getAnoConclusao());
        assertEquals(Integer.valueOf(2000), item1.getAnoInicio());
        assertEquals("descricao", item1.getDescricao());
        assertEquals(Long.valueOf(1), item1.getIdFormacaoAcademica());
        assertEquals("nomeInstituicao", item1.getNomeInstituicao());
        assertEquals("tipo", item1.getTipo());
        assertEquals(Long.valueOf(1), item1.getPesquisador().getIdUsuario());
    }
}