package br.ufscar.rcms.view.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.servico.ConfiguracaoService;
import br.ufscar.rcms.servico.ProducaoService;
import br.ufscar.rcms.servico.ProjetoPesquisaService;

@RequestScoped
@ManagedBean(name = "indiceMB")
public class IndiceMB extends AbstractMB {

    private static final long serialVersionUID = -6437110098010967429L;

    @ManagedProperty("#{configuracaoService}")
    private ConfiguracaoService configuracaoService;

    @ManagedProperty("#{projetoPesquisaService}")
    private ProjetoPesquisaService projetoPesquisaService;

    @ManagedProperty("#{producaoService}")
    private ProducaoService producaoService;

    private List<ProjetoPesquisa> projetos;

    private List<Producao> producoes;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    @Override
    protected void limparDados() {
    }

    @Override
    protected void carregarDados() {
        this.projetos = new ArrayList<ProjetoPesquisa>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PROJETO_PESQUISA)) {
            this.projetos.add(projetoPesquisaService.buscar(((ConfiguracaoIndice) configuracao).getId()));
        }

        this.producoes = new ArrayList<Producao>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PRODUCAO)) {
            this.producoes.add(producaoService.buscarPorId(((ConfiguracaoIndice) configuracao).getId()));
        }
    }

    public ConfiguracaoService getConfiguracaoService() {
        return configuracaoService;
    }

    public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    public ProjetoPesquisaService getProjetoPesquisaService() {
        return projetoPesquisaService;
    }

    public void setProjetoPesquisaService(ProjetoPesquisaService projetoPesquisaService) {
        this.projetoPesquisaService = projetoPesquisaService;
    }

    public ProducaoService getProducaoService() {
        return producaoService;
    }

    public void setProducaoService(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    public List<ProjetoPesquisa> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<ProjetoPesquisa> projetos) {
        this.projetos = projetos;
    }

    public List<Producao> getProducoes() {
        return producoes;
    }

    public void setProducoes(List<Producao> producoes) {
        this.producoes = producoes;
    }

    public Integer getColunasParaProjetos() {
        return 12 / this.projetos.size();
    }

    public Integer getColunasParaProducoes() {
        return 12 / this.producoes.size();
    }
}
