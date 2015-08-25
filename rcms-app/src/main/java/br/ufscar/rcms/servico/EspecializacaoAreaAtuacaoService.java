package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;

public interface EspecializacaoAreaAtuacaoService extends Serializable {
	
	List<EspecializacaoAreaAtuacao> buscarTodas();
	void remover(EspecializacaoAreaAtuacao espec);
	void salvar(EspecializacaoAreaAtuacao espec);
	
}
