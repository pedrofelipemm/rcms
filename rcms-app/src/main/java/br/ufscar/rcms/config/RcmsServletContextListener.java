package br.ufscar.rcms.config;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.factory.AutorizacaoFactory;
import br.ufscar.rcms.factory.PesquisadorFactory;
import br.ufscar.rcms.modelo.entidades.Autorizacao;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.AutorizacaoService;
import br.ufscar.rcms.servico.PesquisadorService;

@Component
@Transactional
// TODO PEDRO RENAME
public class RcmsServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AutorizacaoService autorizacaoService;

    @Autowired
    private PesquisadorService pesquisadorService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        insertRoles();
        insertResearcher();
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
}