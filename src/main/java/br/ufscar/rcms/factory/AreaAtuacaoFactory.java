package br.ufscar.rcms.factory;

import java.util.ArrayList;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;

public abstract class AreaAtuacaoFactory {

    public static GrandeAreaAtuacao createGrandeAreaEmpty() {
		GrandeAreaAtuacao ret = new GrandeAreaAtuacao();
		ret.setAreasDeAtuacao(new ArrayList<AreaAtuacao>());
		return ret;
	}

    public static AreaAtuacao createAreaAtuacaoEmpty() {
		AreaAtuacao ret = new AreaAtuacao();
		ret.setSubAreasAtuacao(new ArrayList<SubAreaAtuacao>());
		return ret;
	}
}
