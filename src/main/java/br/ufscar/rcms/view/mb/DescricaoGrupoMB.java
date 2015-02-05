package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufscar.rcms.modelo.entidades.DescricaoGrupo;
import br.ufscar.rcms.servico.DescricaoGrupoService;

@ViewScoped
@ManagedBean(name = "descricaoGrupoMB")
public class DescricaoGrupoMB extends AbstractMB {
    
    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{descricaoGrupoService}")
    private DescricaoGrupoService descricaoGrupoService;

    private DescricaoGrupo descricaoGrupo;

    @PostConstruct
    public void inicializar() {

    }

    public void salvar() {
        
        if (descricaoGrupo.getDescricao() != null) {

            descricaoGrupoService.alterar(descricaoGrupo);
            adicionarMensagemInfoByKey("descricao.grupo.salva.sucesso", descricaoGrupo.getDescricao());
        } else {
            descricaoGrupoService.salvar(descricaoGrupo);
            adicionarMensagemInfoByKey("descricao.grupo.salva.sucesso", descricaoGrupo.getDescricao());
        }
    }

    public DescricaoGrupoService getDescricaoGrupoService() {
        return descricaoGrupoService;
    }

    public void setDescricaoGrupoService(DescricaoGrupoService descricaoGrupoService) {
        this.descricaoGrupoService = descricaoGrupoService;
    }

    public DescricaoGrupo getDescricaoGrupo() {
        return descricaoGrupo;
    }

    public void setDescricaoGrupo(DescricaoGrupo descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }
}
