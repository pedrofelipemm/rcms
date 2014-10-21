package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;

@ViewScoped
@ManagedBean(name = "areaAtuacaoMB")
public class AreaAtuacaoMB extends AbstractMB {

    private static final long serialVersionUID = 6030291216383161652L;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;

    private AreaAtuacao area;

    @PostConstruct
    public void inicializar() {
        area = new AreaAtuacao();
    }

    public void salvar() {
        if (area != null) {
            AreaAtuacao areaExistente = areaAtuacaoService.buscarPorDescricao(area.getDescricao());

            if (areaExistente == null) {
                areaAtuacaoService.salvar(area);
                adicionarMensagemInfoByKey("area.atuacao.cadastrada.sucesso", area.getDescricao());
            } else {
                adicionarMensagemAlertaByKey("area.atuacao.existente", area.getDescricao());
            }

        }
    }

    public AreaAtuacaoService getAreaAtuacaoService() {
        return areaAtuacaoService;
    }

    public void setAreaAtuacaoService(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    public AreaAtuacao getArea() {
        return area;
    }

    public void setArea(AreaAtuacao area) {
        this.area = area;
    }
}
