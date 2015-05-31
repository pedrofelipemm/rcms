package br.ufscar.rcms.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.factory.CompreensaoIdiomaFactory;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class PesquisadorDAOTest extends AbstractDAOTestBase {

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Before
    public void init() {

        Idioma idioma = new Idioma("Ingles");
        idioma = (Idioma) salvar(idioma);

        //TODO PEDRO MOVE TO PESQUISADOR BUILDER
        Endereco endereco = new Endereco();
        endereco.setEnderecoProfissional("Teste");
        Pesquisador pesquisador = new PesquisadorBuilder("login", "nome", "senha", "codigoLattes", "email", true, "resumoProfissional").
                endereco(endereco).build();
        pesquisador = (Pesquisador) salvar(pesquisador);

        CompreensaoIdioma compreensaoIdioma = CompreensaoIdiomaFactory.createCompreensaoIdioma(idioma, "bla",
                pesquisador);
        pesquisador.addCompreensaoIdiomas(compreensaoIdioma);
        salvar(pesquisador);

        Endereco endereco2 = new Endereco();
        endereco2.setEnderecoProfissional("Teste2");
        Pesquisador pesquisador2 = new PesquisadorBuilder("login2", "nome2", "senha2", "codigoLattes2", "email2", true, "resumoProfissional2").
                endereco(endereco2).build();
        pesquisador2 = (Pesquisador) salvar(pesquisador2);

        CompreensaoIdioma compreensaoIdioma2 = CompreensaoIdiomaFactory.createCompreensaoIdioma(idioma, "bla",
                pesquisador2);
        pesquisador2.addCompreensaoIdiomas(compreensaoIdioma2);
        salvar(pesquisador2);

    }

    @Test
    public void buscarPesquisadoresComIdiomaTest() {

        Assert.assertEquals(2, pesquisadorDAO.buscarTodosComIdioma(1L).size());
        Assert.assertEquals(0, pesquisadorDAO.buscarTodosComIdioma(2L).size());
    }
}