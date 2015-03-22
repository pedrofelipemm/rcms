package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.LinhaDePesquisa;

public abstract class LinhaDePesquisaFactory {

    public static LinhaDePesquisa createLinhaDePesquisa(Long idLinhaDePesquisa, String descricao) {
        LinhaDePesquisa linhaDePesquisa = new LinhaDePesquisa();
        linhaDePesquisa.setIdLinhaDePesquisa(idLinhaDePesquisa);
        linhaDePesquisa.setDescricao(descricao);
        return linhaDePesquisa;
    }
}
