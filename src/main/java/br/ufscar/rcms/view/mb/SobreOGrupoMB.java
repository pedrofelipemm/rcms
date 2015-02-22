package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufscar.rcms.modelo.entidades.SobreOGrupo;
import br.ufscar.rcms.servico.SobreOGrupoService;

@ViewScoped
@ManagedBean(name = "sobreOGrupoMB")
public class SobreOGrupoMB extends AbstractMB {
    
    private static final long serialVersionUID = -6107710930010996528L;

    @ManagedProperty("#{sobreOGrupoService}")
    private SobreOGrupoService sobreOGrupoService;

    private SobreOGrupo sobreOGrupo;

    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    protected void carregarDados() {

        SobreOGrupo sobreOGrupoEdicao = (SobreOGrupo) getFlashObject(FLASH_KEY_SOBRE_O_GRUPO);

        if (sobreOGrupoEdicao != null) {
            sobreOGrupo = sobreOGrupoEdicao;
        }
    }

    protected void limparDados() {

        sobreOGrupo = new SobreOGrupo();
    }

    public void salvar() {

        if (sobreOGrupo != null) {

            sobreOGrupoService.alterar(sobreOGrupo);
            adicionarMensagemInfoByKey("sobre.o.grupo.salvo.sucesso");
        } else {
            sobreOGrupoService.salvar(sobreOGrupo);
            adicionarMensagemInfoByKey("sobre.o.grupo.salvo.sucesso");
        }
    }

    public String exibir(String idioma) {

        sobreOGrupo = sobreOGrupoService.buscarPorIdioma(idioma);

        setFlashObject(FLASH_KEY_SOBRE_O_GRUPO, sobreOGrupo);

        return EXIBE_SOBRE_O_GRUPO;
    }

    public String editar(String idioma) {

        sobreOGrupo = sobreOGrupoService.buscarPorIdioma(idioma);

        if (sobreOGrupo == null) {
            sobreOGrupo = new SobreOGrupo();
            sobreOGrupo.setIdioma(idioma);
        }

        setFlashObject(FLASH_KEY_SOBRE_O_GRUPO, sobreOGrupo);

        return CADASTRO_SOBRE_O_GRUPO;
    }

    public SobreOGrupoService getSobreOGrupoService() {
        return sobreOGrupoService;
    }

    public void setSobreOGrupoService(SobreOGrupoService sobreOGrupoService) {
        this.sobreOGrupoService = sobreOGrupoService;
    }

    public SobreOGrupo getSobreOGrupo() {
        return sobreOGrupo;
    }

    public void setSobreOGrupo(SobreOGrupo sobreOGrupo) {
        this.sobreOGrupo = sobreOGrupo;
    }

}
