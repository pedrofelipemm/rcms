package br.ufscar.rcms.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;

public abstract class AreaAtuacaoFactory {

	public static GrandeAreaAtuacao CreateGrandeAreaEmpty(){
		GrandeAreaAtuacao ret = new GrandeAreaAtuacao();
		ret.setAreasDeAtuacao(new ArrayList<AreaAtuacao>());
		return ret;
	}
	
	public static AreaAtuacao CreateAreaAtuacaoEmpty(){
		AreaAtuacao ret = new AreaAtuacao();
		ret.setSubAreasAtuacao(new ArrayList<SubAreaAtuacao>());
		return ret;
	}
}
