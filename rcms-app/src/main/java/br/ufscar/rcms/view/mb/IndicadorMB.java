package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;

import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.scorecard.commons.rest.Wrapper;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByResearcherDTO;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByYearDTO;
import br.ufscar.rcms.servico.ConfiguracaoService;
import br.ufscar.rcms.view.util.RestClient;

@ViewScoped
@ManagedBean(name = "indicadorMB")
public class IndicadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicadorMB.class);

    @ManagedProperty("#{restClient}")
    private RestClient restClient;

    @ManagedProperty("#{configuracaoService}")
    private ConfiguracaoService configuracaoService;

    private List<AmountProducaoByResearcherDTO> producoesByResearcher;
    private List<AmountProducaoByYearDTO> producoesByYear;
    private Set<String> servicosIndisponiveis = new HashSet<String>();

    private Map<String, ChartModel> charts = new HashMap<String, ChartModel>();

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
        carregarGraficos();
    }

    @Override
    protected void carregarDados() {

        producoesByResearcher = findAmountProducoesByResearcher();
        producoesByYear = findAmountProducoesByYear();
    }

    @Override
    protected void limparDados() {}

    private void carregarGraficos() {
        if (!isEmpty(producoesByYear)) {
            charts.put("lineChartByYear", createLineChartByYearl(producoesByYear));
        }

        if (!isEmpty(producoesByResearcher)) {
            charts.put("barChartByResearcher", createBarChartByResearcher(producoesByResearcher));
        }
    }

    public List<AmountProducaoByYearDTO> findAmountProducoesByYear() {

        String url = configuracaoService.buscarPorTipo(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR).get(0).getValue();
        List<AmountProducaoByYearDTO> entity = null;

        try {
            entity = restClient.getForEntity(url, new TypeToken<Wrapper<AmountProducaoByYearDTO>>() {}.getType());
            servicosIndisponiveis.remove(url);
        } catch (final Exception exception) {
            handleFault(url, exception);
        }

        return entity;
    }

    public List<AmountProducaoByResearcherDTO> findAmountProducoesByResearcher() {

        String url = configuracaoService.buscarPorTipo(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_RESEARCHER).get(0).getValue();
        List<AmountProducaoByResearcherDTO> entity = null;

        try {
            entity = restClient.getForEntity(url, new TypeToken<Wrapper<AmountProducaoByResearcherDTO>>() {}.getType());
            servicosIndisponiveis.remove(url);
        } catch (final Exception exception) {
            handleFault(url, exception);
        }

        return entity;
    }

    private LineChartModel createLineChartByYearl(final List<AmountProducaoByYearDTO> producoesByYear) {

        LineChartSeries series = new LineChartSeries();
        series.setFill(true);

        LineChartModel chart = new LineChartModel();
        chart.addSeries(series);
        chart.setStacked(true);
        chart.setShowPointLabels(true);

        producoesByYear.forEach(t -> series.set(t.getYear(), t.getAmount()));

        Axis xAxis = new CategoryAxis(getMessage("ano"));
        chart.getAxes().put(AxisType.X, xAxis);
        xAxis.setTickAngle(-50);

        Axis yAxis = chart.getAxis(AxisType.Y);
        yAxis.setLabel(getMessage("quantidade"));
        yAxis.setTickFormat("%d");

        yAxis.setMin(0);
        yAxis.setMax(producoesByYear.stream().max((t1, t2) -> t1.getAmount().compareTo(t2.getAmount())).get().getAmount() + 1);

        return chart;
    }

    private BarChartModel createBarChartByResearcher(final List<AmountProducaoByResearcherDTO> producoesByResearcher) {

        BarChartSeries series = new BarChartSeries();
        BarChartModel chart = new BarChartModel();

        producoesByResearcher.sort((t1, t2) -> t1.getName().compareTo(t2.getName()));
        producoesByResearcher.forEach(t -> series.set(t.getName(), t.getAmount()));

        chart.addSeries(series);

        Axis xAxis = new CategoryAxis(getMessage("pesquisadores"));
        chart.getAxes().put(AxisType.X, xAxis);
        xAxis.setTickAngle(-25);

        Axis yAxis = chart.getAxis(AxisType.Y);
        yAxis.setLabel(getMessage("quantidade"));
        yAxis.setTickFormat("%d");

        return chart;
    }

    private void handleFault(final String url, final Exception exception) {
        String mensagem = getMessage("falha.servico", url);
        servicosIndisponiveis.add(url);
        adicionarMensagemErro(mensagem);
        LOGGER.error(mensagem, exception);
    }

    public List<AmountProducaoByResearcherDTO> getProducoesByResearcher() {
        return producoesByResearcher;
    }

    public List<AmountProducaoByYearDTO> getProducoesByYear() {
        return producoesByYear;
    }

    public RestClient getRestClient() {
        return restClient;
    }

    public void setRestClient(final RestClient restClient) {
        this.restClient = restClient;
    }

    public void setProducoesByResearcher(final List<AmountProducaoByResearcherDTO> producoesByResearcher) {
        this.producoesByResearcher = producoesByResearcher;
    }

    public void setProducoesByYear(final List<AmountProducaoByYearDTO> producoesByYear) {
        this.producoesByYear = producoesByYear;
    }

    public Map<String, ChartModel> getCharts() {
        return charts;
    }

    public void setCharts(final Map<String, ChartModel> charts) {
        this.charts = charts;
    }

    public List<String> getListaServicosIndisponiveis() {
        return new ArrayList<String>(servicosIndisponiveis);
    }

    public ConfiguracaoService getConfiguracaoService() {
        return configuracaoService;
    }

    public void setConfiguracaoService(final ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }
}