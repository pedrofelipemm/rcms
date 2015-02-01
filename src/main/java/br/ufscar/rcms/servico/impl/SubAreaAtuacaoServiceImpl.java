package br.ufscar.rcms.servico.impl;

import br.ufscar.rcms.dao.SubAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.SubAreaAtuacaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("subAreaAtuacaoService")
@Transactional
public class SubAreaAtuacaoServiceImpl
  implements SubAreaAtuacaoService
{
  private static final long serialVersionUID = 6385315929837094245L;
  @Autowired
  private SubAreaAtuacaoDAO subAreaDAO;
  
  public void salvar(SubAreaAtuacao subArea)
  {
    this.subAreaDAO.salvar(subArea);
  }
  
  public void alterar(SubAreaAtuacao subArea)
  {
    this.subAreaDAO.atualizar(subArea);
  }
  
  public void remover(SubAreaAtuacao subArea)
  {
    this.subAreaDAO.remover(subArea);
  }
  
  public List<SubAreaAtuacao> buscarTodas()
  {
    return this.subAreaDAO.buscarTodos();
  }
  
  public List<SubAreaAtuacao> buscarPorDescricao(String Descricao)
  {
    return this.subAreaDAO.buscarPorDescricao(Descricao);
  }
}
