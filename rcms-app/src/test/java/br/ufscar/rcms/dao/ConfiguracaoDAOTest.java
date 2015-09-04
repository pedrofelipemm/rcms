package br.ufscar.rcms.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.factory.ConfiguracaoFactory;
import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoUsuario;
import br.ufscar.rcms.modelo.entidades.Usuario;
import br.ufscar.rcms.util.Fixture;

public class ConfiguracaoDAOTest extends AbstractDAOTestBase {

    @Autowired
    private ConfiguracaoDAO configuracaoDAO;

    @Before
    public void setUp() {

        Usuario usuario = Fixture.newUsuario();

        Configuracao config1 = ConfiguracaoFactory.createConfiguracao(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR);
        Configuracao config2 = ConfiguracaoFactory.createConfiguracao(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_RESEARCHER);
        ConfiguracaoUsuario config3 = (ConfiguracaoUsuario) ConfiguracaoFactory.createConfiguracao(Tipo.ESTILO_PORTAL, usuario);
        ConfiguracaoUsuario config4 = (ConfiguracaoUsuario) ConfiguracaoFactory.createConfiguracao(Tipo.IDIOMA, usuario);

        usuario.addConfiguracao(config3);
        usuario.addConfiguracao(config4);

        salvar(config1, config2, usuario);
    }

    @Test
    public void buscarPorTipoTest() {

        List<Configuracao> configs = configuracaoDAO.buscarPorTipo(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR,
                Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR);

        assertEquals(2, configs.size());
    }

    @Test
    public void buscarPorTiposTest() {

        List<Configuracao> configs = configuracaoDAO.buscarPorTipo(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR,
                Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR, Tipo.IDIOMA);

        assertEquals(4, configs.size());
    }
}