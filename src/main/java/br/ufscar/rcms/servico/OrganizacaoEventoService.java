package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.OrganizacaoEvento;

public interface OrganizacaoEventoService extends Serializable {

	void salvar(OrganizacaoEvento organizacaoEvento);

	void salvarOuAtualizar(OrganizacaoEvento organizacaoEvento);

	void remover(OrganizacaoEvento organizacaoEvento);
	
	void remover(List<OrganizacaoEvento> organizacaoEvento);

	List<OrganizacaoEvento> buscarTodas();

}
