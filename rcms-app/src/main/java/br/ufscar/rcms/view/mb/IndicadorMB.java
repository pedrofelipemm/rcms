package br.ufscar.rcms.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;

import br.ufscar.rcms.scorecard.commons.rest.Wrapper;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByResearcherDTO;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByYearDTO;
import br.ufscar.rcms.view.util.RestClient;

@ViewScoped
@ManagedBean(name = "indicadorMB")
public class IndicadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicadorMB.class);

    @ManagedProperty("#{restClient}")
    private RestClient restClient;

    private List<AmountProducaoByResearcherDTO> producoesByResearcher;
    private List<AmountProducaoByYearDTO> producoesByYear;

    private LineChartModel lineChartByYear;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
        lineChartByYear = createLineChartByYearl();
    }

    @Override
    protected void carregarDados() {

        producoesByResearcher = findAmountProducoesByResearcher();
        producoesByYear = findAmountProducoesByYear();

    }

    @Override
    protected void limparDados() {}

    public List<AmountProducaoByYearDTO> findAmountProducoesByYear() {

        // TODO PEDRO EXTRACT URL
        String url = "http://localhost:23081/rcms-scorecard/api/producoes/amount/year";
        List<AmountProducaoByYearDTO> entity = null;

        try {
            entity = restClient.getForEntity(url, new TypeToken<Wrapper<AmountProducaoByYearDTO>>() {}.getType());
        } catch (final Exception exception) {
            String mensagem = getMessage("falha.servico", url);
            adicionarMensagemErro(mensagem);
            LOGGER.error(mensagem, exception);
        }

        return entity;
    }

    public List<AmountProducaoByResearcherDTO> findAmountProducoesByResearcher() {

        // TODO PEDRO EXTRACT URL
        String url = "http://localhost:23081/rcms-scorecard/api/producoes/amount/researcher";
        List<AmountProducaoByResearcherDTO> entity = null;

        try {
            entity = restClient.getForEntity(url, new TypeToken<Wrapper<AmountProducaoByResearcherDTO>>() {}.getType());
        } catch (final Exception exception) {
            String mensagem = getMessage("falha.servico", url);
            adicionarMensagemErro(mensagem);
            LOGGER.error(mensagem, exception);
        }

        return entity;
    }

    private LineChartModel createLineChartByYearl() {

        LineChartSeries year = new LineChartSeries();
        year.setFill(true);
        year.setLabel(getMessage("producoes"));

        LineChartModel chart = new LineChartModel();
        chart.addSeries(year);
        chart.setTitle("Test");// TODO PEDRO
        chart.setLegendPosition("ne");
        chart.setStacked(true);
        chart.setShowPointLabels(true);

        producoesByYear.forEach(t -> year.set(t.getYear(), t.getAmount()));

        Axis xAxis = new CategoryAxis("Test2");// TODO PEDRO
        chart.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = chart.getAxis(AxisType.Y);
        yAxis.setLabel("Test3");// TODO PEDRO

        yAxis.setMin(0);
        yAxis.setMax(producoesByYear.stream().max((t1, t2) -> t1.getAmount().compareTo(t2.getAmount())).get().getAmount() + 1);

        return chart;
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

    public LineChartModel getLineChartByYear() {
        return lineChartByYear;
    }

    public void setLineChartByYear(final LineChartModel lineChartByYear) {
        this.lineChartByYear = lineChartByYear;
    }
}