package br.ufscar.rcms.view.mb;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import com.google.gson.reflect.TypeToken;

import br.ufscar.rcms.commons.util.JsonUtil;
import br.ufscar.rcms.scorecard.commons.rest.Wrapper;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByResearcherDTO;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByYearDTO;

@ViewScoped
@ManagedBean(name = "indicadorMB")
public class IndicadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicadorMB.class);

    @Autowired
    private RestOperations client;

    private List<AmountProducaoByResearcherDTO> producoesByResearcher;
    private List<AmountProducaoByYearDTO> producoesByYear;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    @Override
    protected void carregarDados() {

        producoesByResearcher = findAmountProducoesByResearcher();
        producoesByYear = findAmountProducoesByYear();

    }

    @Override
    protected void limparDados() {}

    public List<AmountProducaoByYearDTO> findAmountProducoesByYear() {

        List<AmountProducaoByYearDTO> result = null;

        // TODO PEDRO EXTRACT URL
        String url = "http://localhost:23081/rcms-scorecard/api/producoes/amount/year";
        try {
            ResponseEntity<String> response = client.getForEntity(url, String.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                Type type = new TypeToken<Wrapper<AmountProducaoByYearDTO>>() {
                }.getType();
                Wrapper<AmountProducaoByYearDTO> wrapper = JsonUtil.getInstance().fromJson(response.getBody(), type);

                result = wrapper.getData();
            }

        } catch (final Exception exception) {
            String mensagem = getMessage("falha.servico", url);
            adicionarMensagemErroByKey(mensagem);
            LOGGER.error(mensagem, exception);
        }

        return result;
    }

    public List<AmountProducaoByResearcherDTO> findAmountProducoesByResearcher() {

        List<AmountProducaoByResearcherDTO> result = null;

        // TODO PEDRO EXTRACT URL
        String url = "http://localhost:23081/rcms-scorecard/api/producoes/amount/researcher";
        try {
            ResponseEntity<String> response = client.getForEntity(url, String.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                Type type = new TypeToken<Wrapper<AmountProducaoByResearcherDTO>>() {}.getType();
                Wrapper<AmountProducaoByResearcherDTO> wrapper = JsonUtil.getInstance().fromJson(response.getBody(), type);

                result = wrapper.getData();
            }

            throw new RuntimeException();
        } catch (final Exception exception) {
            String mensagem = getMessage("falha.servico", url);
            adicionarMensagemErroByKey(mensagem);
            LOGGER.error(mensagem, exception);
        }

        return result;
    }

    public List<AmountProducaoByResearcherDTO> getProducoesByResearcher() {
        return producoesByResearcher;
    }

    public List<AmountProducaoByYearDTO> getProducoesByYear() {
        return producoesByYear;
    }

    public RestOperations getClient() {
        return client;
    }

    public void setClient(RestOperations client) {
        this.client = client;
    }
}