package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.Orientacao;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public abstract class OrientacaoFactory {

    public static Orientacao createOrientacao(Long idOrientacao, Pesquisador pesquisador, String nomeDoAluno,
            String instituicao, String agenciaDeFomento, String tipoDeOrientacao, String situacao,
            String tituloTrabalho, Class<? extends Orientacao> clazz) throws InstantiationException, IllegalAccessException {

        Orientacao orientacao = clazz.newInstance();
        orientacao.setAgenciaDeFomento(agenciaDeFomento);
        orientacao.setIdOrientacao(idOrientacao);
        orientacao.setInstituicao(instituicao);
        orientacao.setNomeDoAluno(nomeDoAluno);
        orientacao.setPesquisador(pesquisador);
        orientacao.setSituacao(situacao);
        orientacao.setTipoDeOrientacao(tipoDeOrientacao);
        orientacao.setTituloTrabalho(tituloTrabalho);

        return orientacao;
    }

    public static Orientacao createOrientacao(Pesquisador pesquisador, String nomeDoAluno, String instituicao,
            String agenciaDeFomento, String tipoDeOrientacao, String situacao, String tituloTrabalho,
            Class<? extends Orientacao> clazz) throws InstantiationException, IllegalAccessException {

        return createOrientacao(null, pesquisador, nomeDoAluno, instituicao, agenciaDeFomento, tipoDeOrientacao,
                situacao, tituloTrabalho, clazz);
    }

    public static Orientacao createOrientacao(Pesquisador pesquisador, String nomeDoAluno, String instituicao,
            String agenciaDeFomento, String tipoDeOrientacao, String tituloTrabalho, Class<? extends Orientacao> clazz)
            throws InstantiationException, IllegalAccessException {

        return createOrientacao(null, pesquisador, nomeDoAluno, instituicao, agenciaDeFomento, tipoDeOrientacao, null,
                tituloTrabalho, clazz);
    }
}