package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Autorizacao;

public interface AutorizacaoService extends Serializable {
	
	List<Autorizacao> buscarTodos();
	
	Autorizacao salvar(Autorizacao autorizacao);

}
