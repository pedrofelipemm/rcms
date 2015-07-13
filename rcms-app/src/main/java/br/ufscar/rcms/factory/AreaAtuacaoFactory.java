package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import java.util.ArrayList;

public abstract class AreaAtuacaoFactory
{
  public static GrandeAreaAtuacao createGrandeAreaEmpty()
  {
    GrandeAreaAtuacao ret = new GrandeAreaAtuacao();
    ret.setAreasDeAtuacao(new ArrayList());
    return ret;
  }
  
  public static AreaAtuacao createAreaAtuacaoEmpty()
  {
    AreaAtuacao ret = new AreaAtuacao();
    ret.setSubAreasAtuacao(new ArrayList());
    return ret;
  }
  
  public static SubAreaAtuacao createSubAreaAtuacaoEmpty()
  {
    SubAreaAtuacao ret = new SubAreaAtuacao();
    ret.setEspecializacoes(new ArrayList());
    return ret;
  }
}
