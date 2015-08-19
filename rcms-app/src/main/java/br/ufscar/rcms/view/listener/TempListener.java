package br.ufscar.rcms.view.listener;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.factory.ConfiguracaoFactory;
import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.servico.ConfiguracaoService;

@Component
@Transactional
// TODO PEDRO - MERGE RcmsServletContextListener
public class TempListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        insertMicroservicesUrls();
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