package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="GRANDE_AREA_ATUACAO")
public class GrandeAreaAtuacao extends Entidade{

	private static final long serialVersionUID = -3303227387678912075L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
    private String descricao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AreaAtuacao> areasDeAtuacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
	
	public List<AreaAtuacao> getAreasDeAtuacao() {
		return areasDeAtuacao;
	}
	public void setAreasDeAtuacao(List<AreaAtuacao> areasDeAtuacao) {
		this.areasDeAtuacao = areasDeAtuacao;
	}

}
