package br.ufscar.rcms.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.AutorProducao;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.CitacaoBibliograficaService;

public class ProducaoDAOTest extends AbstractDAOTestBase {

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private ProducaoDAO producaoDAO;

    @Autowired
    private CitacaoBibliograficaService citacaoBibliograficaService;

    @Before
    public void init() {
        Endereco endereco = new Endereco();
        endereco.setEnderecoProfissional("Teste");
        merge(new PesquisadorBuilder("login", "nome", "senha", "codigoLattes", "email", "resumoProfissional").endereco(endereco).build());
    }

    // TODO PEDRO @Test
    public void buscarProducoesTest() {

        Pesquisador pesquisador = pesquisadorDAO.buscarTodos().get(0);
        ArtigoEmPeriodico artigoEmPeriodico = new ArtigoEmPeriodico("titulo", 2000, "vol.1", "200", "doi", "revista", 1);
        List<AutorProducao> autores = Arrays.asList(
                new AutorProducao(artigoEmPeriodico, citacaoBibliograficaService.buscarPorNomeCitacao("nome"), 1));

        artigoEmPeriodico.setAutores(autores);
        merge(artigoEmPeriodico);

        List<ArtigoEmPeriodico> artigos = producaoDAO.buscarProducoes(ArtigoEmPeriodico.class,
                pesquisador.getIdUsuario());
        assertEquals(1, artigos.size());

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