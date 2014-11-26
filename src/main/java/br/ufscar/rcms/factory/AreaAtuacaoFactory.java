package br.ufscar.rcms.factory;

import java.util.HashSet;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;

public abstract class AreaAtuacaoFactory {

	public static GrandeAreaAtuacao CreateGrandeAreaEmpty(){
		GrandeAreaAtuacao ret = new GrandeAreaAtuacao();
		ret.setAreasDeAtuacao(new HashSet<AreaAtuacao>());
		return ret;
	}
	
	public static AreaAtuacao CreateAreaAtuacaoEmpty(){
		AreaAtuacao ret = new AreaAtuacao();
		ret.setSubAreasAtuacao(new HashSet<SubAreaAtuacao>());
		return ret;
	}
}
