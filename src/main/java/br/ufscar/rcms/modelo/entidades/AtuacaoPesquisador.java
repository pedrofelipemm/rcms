package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.junit.Ignore;

@Entity
@Table(name = "\"ATUACAO\"")
public class AtuacaoPesquisador extends Entidade{

	private static final long serialVersionUID = -6748580184423008322L;
	
	public AtuacaoPesquisador(){
		
	}
	
	private int index;
	
	public AtuacaoPesquisador(GrandeAreaAtuacao grandeArea, AreaAtuacao area,
			SubAreaAtuacao subArea, EspecializacaoAreaAtuacao especializacao, Pesquisador pesquisador){
		this.grandeArea = grandeArea;
		this.area = area;
		this.subArea = subArea;
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
	private int IdAtuacao;
	
	@ManyToOne(optional = false)
	private Pesquisador pesquisador;
	
	private GrandeAreaAtuacao grandeArea;
	private AreaAtuacao area;
	private SubAreaAtuacao subArea;
	private EspecializacaoAreaAtuacao especializacao;
	
	
	public GrandeAreaAtuacao getGrandeArea() {
		return grandeArea;
	}
	
	public void setGrandeArea(GrandeAreaAtuacao grandeArea) {
		this.grandeArea = grandeArea;
	}
	
	public AreaAtuacao getArea() {
		return area;
	}
	
	public void setArea(AreaAtuacao area) {
		this.area = area;
	}
	
	public SubAreaAtuacao getSubArea() {
		return subArea;
	}
	
	public void setSubArea(SubAreaAtuacao subArea) {
		this.subArea = subArea;
	}
	
	public EspecializacaoAreaAtuacao getEspecializacao() {
		return especializacao;
	}
	
	public void setEspecializacao(EspecializacaoAreaAtuacao especializacao) {
		this.especializacao = especializacao;
	}
	
	@Override
	public String toString(){
		String ret = "";
		
		if (grandeArea != null){
			ret += "Grande área: " +  grandeArea.getDescricao() + " /";
		}
		
		if (area != null){
			ret += " Área: " + area.getDescricao() + " /";
		}
		
		if (subArea != null){
			ret += " Subárea: " + subArea.getDescricao() + " /";
		}
		
		if (especializacao != null){
			ret += " Especialidade: " + especializacao.getDescricao();
		}
		
		if (ret.endsWith("/")){
			ret = String.copyValueOf(ret.toCharArray(), 0, ret.length() - 1);
		}
		
		return ret;
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
