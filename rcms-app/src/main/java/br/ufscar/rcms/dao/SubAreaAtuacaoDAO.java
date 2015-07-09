package br.ufscar.rcms.dao;

import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import java.util.List;

public abstract interface SubAreaAtuacaoDAO
  extends BaseDAO<SubAreaAtuacao, Long>
{
  public abstract List<SubAreaAtuacao> buscarPorDescricao(String paramString);
}
