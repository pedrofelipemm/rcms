package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public abstract class FormacaoAcademicaFactory {

    public static FormacaoAcademica createFormacaoAcademica(Long idFormacaoAcademica, Integer anoConclusao,
            Integer anoInicio, String descricao, String nomeInstituicao, String tipo, Pesquisador pesquisador) {

        FormacaoAcademica formacao = new FormacaoAcademica();
        formacao.setAnoConclusao(anoConclusao);
        formacao.setAnoInicio(anoInicio);
        formacao.setDescricao(descricao);
        formacao.setIdFormacaoAcademica(idFormacaoAcademica);
        formacao.setNomeInstituicao(nomeInstituicao);
        formacao.setPesquisador(pesquisador);
        formacao.setTipo(tipo);

        return formacao;
    }

    public static FormacaoAcademica createFormacaoAcademica(Integer anoConclusao, Integer anoInicio, String descricao,
            String nomeInstituicao, String tipo, Pesquisador pesquisador) {

        return createFormacaoAcademica(null, anoConclusao, anoInicio, descricao, nomeInstituicao, tipo, pesquisador);
    }
}
