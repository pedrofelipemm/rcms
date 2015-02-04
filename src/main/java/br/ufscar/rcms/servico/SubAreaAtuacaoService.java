package br.ufscar.rcms.servico;

import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import java.io.Serializable;
import java.util.List;

public abstract interface SubAreaAtuacaoService
  extends Serializable
{
  public abstract void salvar(SubAreaAtuacao paramSubAreaAtuacao);
  
  public abstract void alterar(SubAreaAtuacao paramSubAreaAtuacao);
  
  public abstract void remover(SubAreaAtuacao paramSubAreaAtuacao);
  
  public abstract List<SubAreaAtuacao> buscarTodas();
  
  public abstract List<SubAreaAtuacao> buscarPorDescricao(String paramString);
}
