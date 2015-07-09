package br.ufscar.rcms.factory;

import java.util.HashMap;
import java.util.Map;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;

public abstract class AtuacaoPesquisadorFactory {

    private static final String KEY_GRANDE_AREA = "Grande área: ";
    private static final String KEY_AREA = "Área: ";
    private static final String KEY_SUBAREA = "Subárea: ";
    private static final String KEY_ESPECIALIDADE = "Especialidade: ";

    public static AtuacaoPesquisador createAtuacaoPesquisador(Pesquisador pesquisador, String descricao) {

        Map<String, String> descricoes = extractDescricoes(descricao);

        AtuacaoPesquisador atuacaoPesquisador = new AtuacaoPesquisador();
        atuacaoPesquisador.setPesquisador(pesquisador);

        GrandeAreaAtuacao grandeAreaAtuacao = new GrandeAreaAtuacao();
        grandeAreaAtuacao.setDescricao(descricoes.get(KEY_GRANDE_AREA));

        AreaAtuacao areaAtuacao = new AreaAtuacao();
        areaAtuacao.setDescricao(descricoes.get(KEY_AREA));
        areaAtuacao.setGrandeAreaAtuacao(grandeAreaAtuacao);

        SubAreaAtuacao subAreaAtuacao = new SubAreaAtuacao();
        subAreaAtuacao.setAreaAtuacao(areaAtuacao);
        subAreaAtuacao.setDescricao(descricoes.get(KEY_SUBAREA));

        EspecializacaoAreaAtuacao especializacao = new EspecializacaoAreaAtuacao();
        especializacao.setDescricao(descricoes.get(KEY_ESPECIALIDADE));
        especializacao.setSubAreaAtuacao(subAreaAtuacao);
        atuacaoPesquisador.setEspecializacao(especializacao);

        grandeAreaAtuacao.addAreaAtuacao(areaAtuacao);
        areaAtuacao.addSubAreaAtuacao(subAreaAtuacao);
        subAreaAtuacao.addEspecializacao(especializacao);

        return atuacaoPesquisador;
    }

    private static Map<String, String> extractDescricoes(String descricao) {

        Map<String, String> descricoes = new HashMap<String, String>(4);
        String[] descricoesArray = descricao.split("/");

        descricoes.put(KEY_GRANDE_AREA, extractDescricao(descricoesArray, KEY_GRANDE_AREA));
        descricoes.put(KEY_AREA, extractDescricao(descricoesArray, KEY_AREA));
        descricoes.put(KEY_SUBAREA, extractDescricao(descricoesArray, KEY_SUBAREA));
        descricoes.put(KEY_ESPECIALIDADE, extractDescricao(descricoesArray, KEY_ESPECIALIDADE));

        return descricoes;
    }

    private static String extractDescricao(String[] descricoes, String key) {

        String result = null;

        for (String descricao : descricoes) {
            if (descricao.contains(key)) {
                result = descricao.substring(key.length()).trim();
                break;
            }
        }

        return result;
    }
}