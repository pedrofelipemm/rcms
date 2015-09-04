package br.ufscar.rcms.view.listener;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.factory.AutorizacaoFactory;
import br.ufscar.rcms.factory.ConfiguracaoFactory;
import br.ufscar.rcms.factory.PesquisadorFactory;
import br.ufscar.rcms.modelo.entidades.Autorizacao;
import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.AutorizacaoService;
import br.ufscar.rcms.servico.ConfiguracaoService;
import br.ufscar.rcms.servico.PesquisadorService;

@Component
@Transactional
public class ApplicationRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AutorizacaoService autorizacaoService;

    @Autowired
    private PesquisadorService pesquisadorService;

    @Autowired
    private ConfiguracaoService configuracaoService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        insertRoles();
        insertResearcher();
        insertMicroservicesUrls();
    }

    private void insertRoles() {
        List<Autorizacao> autorizacoes = autorizacaoService.buscarTodos();
        if (isEmpty(autorizacoes)) {
            autorizacaoService.salvar(AutorizacaoFactory.createAdmin());
            autorizacaoService.salvar(AutorizacaoFactory.createuser());
        }
    }

    private void insertResearcher() {
        List<Pesquisador> pesquisadores = pesquisadorService.buscarTodos();
        if (isEmpty(pesquisadores)) {
            Pesquisador administrador = PesquisadorFactory.createPesquisadorAdmin();
            administrador.addAutorizacao(autorizacaoService.findByRole("ROLE_ADMIN"));

            pesquisadorService.saveOrUpdate(administrador);
        }
    }

    private void insertMicroservicesUrls() {
        Configuracao service1 = ConfiguracaoFactory.createConfiguracao(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR);
        Configuracao service2 = ConfiguracaoFactory.createConfiguracao(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_RESEARCHER);
        List<Configuracao> services = Arrays.asList(service1, service2);

        List<Configuracao> servicesFromDB = configuracaoService.buscarPorTipo(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR,
                Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_RESEARCHER);

        services.stream().filter(t -> !servicesFromDB.contains(t)).forEach(configuracaoService::saveOrUpdate);
    }
}