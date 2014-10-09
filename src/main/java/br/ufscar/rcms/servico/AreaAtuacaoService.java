package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

public interface AreaAtuacaoService extends Serializable {
	
	public void Salvar(AreaAtuacao area);
	public void Alterar(AreaAtuacao area);
	public List<AreaAtuacao> BuscarTodas();
	public AreaAtuacao BuscarPorDescricao(String Descricao);
	 
}
