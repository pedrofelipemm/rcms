package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Idioma;

public interface IdiomaService extends Serializable{
	
	public void Salvar(Idioma idioma);
	public void Alterar(Idioma idioma);
	public List<Idioma> BuscarTodas();
	public Idioma BuscarPorDescricao(String Descricao);

}
