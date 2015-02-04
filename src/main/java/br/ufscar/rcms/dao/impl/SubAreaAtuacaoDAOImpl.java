package br.ufscar.rcms.dao.impl;

import br.ufscar.rcms.dao.SubAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SubAreaAtuacaoDAOImpl
  extends BaseDAOImpl<SubAreaAtuacao, Long>
  implements SubAreaAtuacaoDAO
{
  private static final long serialVersionUID = -8895959396787672129L;
  
  public List<SubAreaAtuacao> buscarPorDescricao(String descricao)
  {
    Query q = getEntityManager().createQuery("SELECT a from SubAreaAtuacao AS a WHERE a.descricao Like '%:d%'");
    
    q.setParameter("d", descricao);
    try
    {
      Object lst = q.getResultList();
      if (lst != null) {
        return (List)lst;
      }
      return null;
    }
    catch (NoResultException noResultException) {}
    return null;
  }
}
