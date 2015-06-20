package br.ufscar.rcms.factory;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.ApresentacaoTrabalho;
import br.ufscar.rcms.modelo.entidades.ArtigoAceitoParaPublicacao;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.CapituloLivro;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.LivroPublicado;
import br.ufscar.rcms.modelo.entidades.OutraProducaoBibliografica;
import br.ufscar.rcms.modelo.entidades.OutraProducaoTecnica;
import br.ufscar.rcms.modelo.entidades.ProcessoOuTecnica;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.ProdutoTecnologico;
import br.ufscar.rcms.modelo.entidades.ResumoCongresso;
import br.ufscar.rcms.modelo.entidades.ResumoExpandidoCongresso;
import br.ufscar.rcms.modelo.entidades.TextoEmJornal;
import br.ufscar.rcms.modelo.entidades.TrabalhoCompletoCongresso;
import br.ufscar.rcms.modelo.entidades.TrabalhoTecnico;

public abstract class ProducaoFactory {
	
	public static Producao CastProducaoByTipo(String tipo, Producao publicacaoBase){
		switch(tipo){
		case "artigoEmPeriodico":
			return publicacaoBase.CloneTo(new ArtigoEmPeriodico());
		case "artigoAceitoPublicacao":
			return publicacaoBase.CloneTo(new ArtigoAceitoParaPublicacao());
		case "livroPublicado":
			return publicacaoBase.CloneTo(new LivroPublicado());
		case "apresentacaoTrabalho":
			return publicacaoBase.CloneTo(new ApresentacaoTrabalho());
		case "capituloLivro":
			return publicacaoBase.CloneTo(new CapituloLivro());
		case "resumoCongresso":
			return publicacaoBase.CloneTo(new ResumoCongresso());
		case "textoJornal":
			return publicacaoBase.CloneTo(new TextoEmJornal());
		case "resumoExpandidoCongresso":
			return publicacaoBase.CloneTo(new ResumoExpandidoCongresso());
		case "trabalhoCompletoCongresso":
			return publicacaoBase.CloneTo(new TrabalhoCompletoCongresso());
		case "outraProducaoBibliografica":
			return publicacaoBase.CloneTo(new OutraProducaoBibliografica());
		case "trabalhoTecnico":
			return publicacaoBase.CloneTo(new TrabalhoTecnico());
		case "outraProducaoTecnica":
			return publicacaoBase.CloneTo(new OutraProducaoTecnica());
		case "produtoTecnologico":
			return publicacaoBase.CloneTo(new ProdutoTecnologico());
		case "processoOuTecnica":
			return publicacaoBase.CloneTo(new ProcessoOuTecnica());
		default:
			return publicacaoBase;
		}
	}

    public static Producao createProducao(String titulo, List<CitacaoBibliografica> citacaoBibliograficas, Integer ano,
            Class<? extends Producao> clazz) throws InstantiationException,
            IllegalAccessException {

        Producao producao = clazz.newInstance();
        producao.setTitulo(titulo);
        producao.setCitacaoBibliograficas(citacaoBibliograficas);
        producao.setAno(ano);

        return producao;
    }
}
