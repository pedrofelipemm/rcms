package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"ESPECIALIZACAO_AREA_ATUACAO\"")
public class EspecializacaoAreaAtuacao
  extends Entidade
{
  private static final long serialVersionUID = 5149973934587512084L;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Column(nullable=false)
  private String descricao;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getDescricao()
  {
    return descricao;
  }

  public void setDescricao(String descricao)
  {
    this.descricao = descricao;
  }
}
