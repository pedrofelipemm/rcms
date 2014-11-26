package br.ufscar.rcms.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufscar.rcms.factory.AreaAtuacaoFactory;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;

@ViewScoped
@ManagedBean(name = "areaAtuacaoMB")
public class AreaAtuacaoMB extends AbstractMB {

    private static final long serialVersionUID = 6030291216383161652L;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;


    private GrandeAreaAtuacao gdeArea;
    private AreaAtuacao area;
    private SubAreaAtuacao subArea;
    
    @PostConstruct
    public void inicializar() {
    	gdeArea = AreaAtuacaoFactory.CreateGrandeAreaEmpty();
    	area = AreaAtuacaoFactory.CreateAreaAtuacaoEmpty();
    	subArea = new SubAreaAtuacao();
    }

    public void salvar() {
    	if (area.getDescricao() != ""){
    		if(subArea.getDescricao() != "")
    			area.getSubAreasAtuacao().add(subArea);
    		gdeArea.getAreasDeAtuacao().add(area);
        }
    	
    	areaAtuacaoService.salvar(gdeArea);
    	adicionarMensagemInfoByKey("area.atuacao.cadastrada.sucesso", gdeArea.getDescricao());
    }

    public AreaAtuacaoService getAreaAtuacaoService() {
        return areaAtuacaoService;
    }

    public void setAreaAtuacaoService(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

	public GrandeAreaAtuacao getGdeArea() {
		return gdeArea;
	}

	public void setGdeArea(GrandeAreaAtuacao gdeArea) {
		this.gdeArea = gdeArea;
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

}
