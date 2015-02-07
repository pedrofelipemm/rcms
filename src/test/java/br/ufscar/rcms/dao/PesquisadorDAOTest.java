package br.ufscar.rcms.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.factory.EnderecoFactory;
import br.ufscar.rcms.factory.FormacaoAcademicaFactory;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.pk.CompreensaoIdiomaPK;

public class PesquisadorDAOTest extends AbstractDAOTestBase {

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Before
    public void init() {

        Pesquisador pesquisador = buildTestPesquisador();

        FormacaoAcademica formacaoAcademica = buildTestFormacao(pesquisador);

        Endereco endereco = EnderecoFactory.createEndereco(1L, "Rua número zê", 10D, -20D);

        pesquisador.setFormacoes(Arrays.asList(formacaoAcademica));
        pesquisador.setEndereco(endereco);

        salvar(pesquisador);
    }

    @Test
    @Ignore
    public void buscarTodosDadosTest() {

        Pesquisador pesquisador = pesquisadorDAO.buscarTodosDados(1L);

        assertEquals(Long.valueOf(1), pesquisador.getIdUsuario());
        assertEquals(Long.valueOf(1), pesquisador.getEndereco().getIdEndereco());
        assertEquals(Long.valueOf(1), pesquisador.getFormacoes().get(0).getIdFormacaoAcademica());
    }

    @Ignore
    @Test
    public void salvarPesquisadorComIdiomas() {

        Pesquisador pesquisador = buildTestPesquisador();
        Idioma idioma = new Idioma();
        idioma.setDescricao("Inglês");

        salvar(pesquisador, idioma);

        idioma = idiomaDAO.buscar(1L);
        assertNotNull(idioma);

        pesquisador = pesquisadorDAO.buscarTodosDados(1L);
        assertNotNull(pesquisador);

        CompreensaoIdioma compreensaoIdioma = new CompreensaoIdioma();
        compreensaoIdioma.setProficiencia("We rock it!");
        compreensaoIdioma.setCompreensaoIdiomaPK(new CompreensaoIdiomaPK(idioma, pesquisador));

        List<CompreensaoIdioma> compreensoes = new ArrayList<CompreensaoIdioma>();
        compreensoes.add(compreensaoIdioma);

        pesquisador.setCompreensaoIdiomas(compreensoes);

        salvar(pesquisador);

        pesquisador = pesquisadorDAO.buscarTodosDados(1L);
        assertNotNull(pesquisador.getCompreensaoIdiomas().get(0));
    }

    private FormacaoAcademica buildTestFormacao(Pesquisador pesquisador) {
        return FormacaoAcademicaFactory.createFormacaoAcademica(1L, 2000, 2000, "descricao", "nomeInstituicao", "tipo",
                pesquisador);
    }

    private Pesquisador buildTestPesquisador() {
        return new PesquisadorBuilder(1L, "jubileu@ufscar.br", "Jubileu", "juju", "3464618468", "jubileu@ufscar.br",
                true, "Lorem Ipsum").build();
    }
}