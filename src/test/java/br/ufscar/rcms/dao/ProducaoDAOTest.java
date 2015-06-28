package br.ufscar.rcms.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class ProducaoDAOTest extends AbstractDAOTestBase {

    // TODO REMOVE
    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private ProducaoDAO producaoDAO;

    @Before
    public void init() {
        Endereco endereco = new Endereco();
        endereco.setEnderecoProfissional("Teste");
        salvar(new PesquisadorBuilder("login", "nome", "senha", "codigoLattes", "email", true, "resumoProfissional").endereco(endereco).build());
    }

    @Test
    public void buscarArtigosEmPeriodicosTest() {

        Pesquisador pesquisador = pesquisadorDAO.buscarTodos().get(0);
        List<CitacaoBibliografica> autores = Arrays.asList(new CitacaoBibliografica(pesquisador, pesquisador.getNome() + " citacação"));
        ArtigoEmPeriodico artigoEmPeriodico = new ArtigoEmPeriodico("titulo", autores, 2000, "vol.1", "200", "doi", "revista", 1);

        salvar(artigoEmPeriodico);

     // List<ArtigoEmPeriodico> artigos = producaoDAO.buscarArtigosEmPeriodicos(pesquisador.getIdUsuario());
        List<ArtigoEmPeriodico> artigos = pesquisadorDAO.buscarArtigosEmPeriodicos(pesquisador.getIdUsuario());
        for (ArtigoEmPeriodico artigo : artigos) {
            assertNotNull(artigo.getAno());
            assertNotNull(artigo.getDoi());
            assertNotNull(artigo.getIdProducao());
            assertNotNull(artigo.getNumero());
            assertNotNull(artigo.getPaginas());
            assertNotNull(artigo.getRevista());
            assertNotNull(artigo.getTitulo());
            assertNotNull(artigo.getVolume());
        }
    }
}