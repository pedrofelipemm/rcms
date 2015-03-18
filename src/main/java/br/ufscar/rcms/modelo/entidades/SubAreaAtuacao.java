package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"SUB_AREA_ATUACAO\"")
public class SubAreaAtuacao
  extends Entidade
{
  private static final long serialVersionUID = -1968382113523281553L;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable=false)
  private String descricao;
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER)
  private List<EspecializacaoAreaAtuacao> especializacoes;
  @ManyToOne
  private AreaAtuacao areaAtuacao;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
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

  public List<EspecializacaoAreaAtuacao> getEspecializacoes()
  {
    return especializacoes;
  }

  public void setEspecializacoes(List<EspecializacaoAreaAtuacao> especializacoes)
  {
    this.especializacoes = especializacoes;
  }

public AreaAtuacao getAreaAtuacao() {
	return areaAtuacao;
}

public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
	this.areaAtuacao = areaAtuacao;
}
}
