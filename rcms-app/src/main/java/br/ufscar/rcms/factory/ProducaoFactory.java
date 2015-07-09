package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.ApresentacaoTrabalho;
import br.ufscar.rcms.modelo.entidades.ArtigoAceitoParaPublicacao;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.CapituloLivro;
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
	
    public static Producao CastProducaoByTipo(String tipo, Producao producaoBase) {
		switch(tipo){
		case "artigoEmPeriodico":
            return producaoBase.CloneTo(new ArtigoEmPeriodico());
		case "artigoAceitoPublicacao":
            return producaoBase.CloneTo(new ArtigoAceitoParaPublicacao());
		case "livroPublicado":
            return producaoBase.CloneTo(new LivroPublicado());
		case "apresentacaoTrabalho":
            return producaoBase.CloneTo(new ApresentacaoTrabalho());
		case "capituloLivro":
            return producaoBase.CloneTo(new CapituloLivro());
		case "resumoCongresso":
            return producaoBase.CloneTo(new ResumoCongresso());
		case "textoJornal":
            return producaoBase.CloneTo(new TextoEmJornal());
		case "resumoExpandidoCongresso":
            return producaoBase.CloneTo(new ResumoExpandidoCongresso());
		case "trabalhoCompletoCongresso":
            return producaoBase.CloneTo(new TrabalhoCompletoCongresso());
		case "outraProducaoBibliografica":
            return producaoBase.CloneTo(new OutraProducaoBibliografica());
		case "trabalhoTecnico":
            return producaoBase.CloneTo(new TrabalhoTecnico());
		case "outraProducaoTecnica":
            return producaoBase.CloneTo(new OutraProducaoTecnica());
		case "produtoTecnologico":
            return producaoBase.CloneTo(new ProdutoTecnologico());
		case "processoOuTecnica":
            return producaoBase.CloneTo(new ProcessoOuTecnica());
		default:
            return producaoBase;
		}
	}

}
