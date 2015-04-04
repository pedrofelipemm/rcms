package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "\"ATUACAO\"")
public class AtuacaoPesquisador extends Entidade{

    private static final long serialVersionUID = -6748580184423008322L;
	
    public AtuacaoPesquisador() {
    }
	
    private int index;
	
    public AtuacaoPesquisador(EspecializacaoAreaAtuacao especializacao, Pesquisador pesquisador) {

        this.especializacao = especializacao;
        this.pesquisador = pesquisador;
    }
	
/*	public AtuacaoPesquisador(GrandeAreaAtuacao grandeArea, AreaAtuacao area,
			SubAreaAtuacao subArea, EspecializacaoAreaAtuacao especializacao){
		descricao = "";
		
		if (grandeArea != null){
			descricao += "Grande área: " +  grandeArea.getDescricao() + " /";
		}
		
		if (area != null){
			descricao += " Área: " + area.getDescricao() + " /";
		}
		
		if (subArea != null){
			descricao += " Subárea: " + subArea.getDescricao() + " /";
		}
		
		if (especializacao != null){
			descricao += " Especialidade: " + especializacao.getDescricao();
		}
		
		if (descricao.endsWith("/")){
			descricao = String.copyValueOf(descricao.toCharArray(), 0, descricao.length() - 1);
		}
		
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdAtuacao;

	
	@Column
	private String descricao;
	*/
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atuacao")
    private int IdAtuacao;
	
    @ManyToOne(optional = false)
    private Pesquisador pesquisador;
	
    @ManyToOne(optional = false)
    private EspecializacaoAreaAtuacao especializacao;
	
    public EspecializacaoAreaAtuacao getEspecializacao() {
        return especializacao;
    }
	
    public void setEspecializacao(EspecializacaoAreaAtuacao especializacao) {
        this.especializacao = especializacao;
    }
	
    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public int getIdAtuacao() {
        return IdAtuacao;
    }

    public void setIdAtuacao(int idAtuacao) {
        IdAtuacao = idAtuacao;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
