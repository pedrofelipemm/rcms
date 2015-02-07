package br.ufscar.rcms.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.factory.EnderecoFactory;
import br.ufscar.rcms.factory.FormacaoAcademicaFactory;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class PesquisadorDAOTest extends AbstractDAOTestBase {

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Before
    public void init() {

        Pesquisador pesquisador = new PesquisadorBuilder(1L, "jubileu@ufscar.br", "Jubileu", "juju", "3464618468",
                "jubileu@ufscar.br", true, "Lorem Ipsum").build();

        FormacaoAcademica formacaoAcademica = FormacaoAcademicaFactory.createFormacaoAcademica(1L, 2000, 2000,
                "descricao", "nomeInstituicao", "tipo", pesquisador);

        Endereco endereco = EnderecoFactory.createEndereco(1L, "Rua número zê", 10D, -20D);

        pesquisador.setFormacoes(Arrays.asList(formacaoAcademica));
        pesquisador.setEndereco(endereco);

        salvar(pesquisador);
    }

    // TODO PEDRO
    @Test
    @Ignore
    public void buscarTodosDadosTest() {

        Pesquisador pesquisador = pesquisadorDAO.buscarTodosDados(1L);

        assertEquals(Long.valueOf(1), pesquisador.getIdUsuario());
        assertEquals(Long.valueOf(1), pesquisador.getEndereco().getIdEndereco());
        assertEquals(Long.valueOf(1), pesquisador.getFormacoes().get(0).getIdFormacaoAcademica());
    }
}