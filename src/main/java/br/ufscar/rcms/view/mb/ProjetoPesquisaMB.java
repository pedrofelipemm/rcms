package br.ufscar.rcms.view.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.ProjetoPesquisaService;
import br.ufscar.rcms.servico.exception.ProjetoPesquisaNaoEncontradoException;

@ViewScoped
@ManagedBean(name = "projetoPesquisaMB")
public class ProjetoPesquisaMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoPesquisaMB.class);

    @ManagedProperty("#{imageCacheMB}")
    private ImageCacheMB cache;

    @ManagedProperty("#{projetoPesquisaService}")
    private ProjetoPesquisaService projetoPesquisaService;
    
    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;
    
    private transient List<Pesquisador> pesquisadores;
    
    private Pesquisador pesquisador;

    private ProjetoPesquisa projetoPesquisa;
    private transient DataModel<ProjetoPesquisa> projetosPesquisa;
    
    private transient Part fotoProjetoPesquisa;
    
    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    protected void carregarDados() {
    	projetosPesquisa = new ListDataModel<ProjetoPesquisa>(projetoPesquisaService.buscarTodos());
    	pesquisadores = new ArrayList<Pesquisador>(pesquisadorService.buscarTodos());

        ProjetoPesquisa projetoPesquisaEdicao = (ProjetoPesquisa) getFlashObject(FLASH_KEY_PROJETO_PESQUISA);
        if (projetoPesquisaEdicao != null) {
            projetoPesquisa = projetoPesquisaService.buscarTodosDados(projetoPesquisaEdicao.getIdProjetoPesquisa());
        }
    }

    protected void limparDados() {
        projetoPesquisa = new ProjetoPesquisa();
        getFlash().clear();
    }

    public String salvar() {

        try {
            projetoPesquisaService.salvarOuAtualizar(projetoPesquisa);
            adicionarMensagemInfoByKey("projetoPesquisa.salvo.sucesso", projetoPesquisa.getNome());

            limparDados();

            keepMessagesOnRedirect();
            return CONSULTA_PROJETO_PESQUISA;
        } catch (Exception exception) {
            adicionarMensagemErroByKey("erro.salvar.projetoPesquisa", projetoPesquisa.getNome());
            LOGGER.error(exception.getMessage(), exception);
        }
        return CONSULTA_PROJETO_PESQUISA;
    }

    public void uploadFile() {
        cache.sendFotoPesquisador(fotoProjetoPesquisa);
    }

    public String exibir(ProjetoPesquisa projetoPesquisa) {

        projetoPesquisa = projetoPesquisaService.buscarTodosDados(projetoPesquisa.getIdProjetoPesquisa());

        setFlashObject(FLASH_KEY_PROJETO_PESQUISA, projetoPesquisa);

        return EXIBE_PROJETO_PESQUISA;
    }

    public String editar(ProjetoPesquisa projetoPesquisa) {

        setFlashObject(FLASH_KEY_PROJETO_PESQUISA, projetoPesquisa);

        return CADASTRO_PROJETO_PESQUISA;
    }

    public String excluir(ProjetoPesquisa projetoPesquisa) {

        try {
            projetoPesquisaService.remover(projetoPesquisa);
        } catch (ProjetoPesquisaNaoEncontradoException e) {
            adicionarMensagemErroByKey("projetoPesquisa.nao.encontrado", projetoPesquisa.getNome());
        }
        limparDados();

        return CONSULTA_PROJETO_PESQUISA;
    }
    
    public void adicionarPequisador() {
    	pesquisador = pesquisadorService.buscarTodosDados(pesquisador.getIdUsuario());
    	projetoPesquisa.adicionarPesquisador(getPesquisador());
    	pesquisadorService.salvarOuAtualizar(pesquisador);
    	setPesquisador(new Pesquisador());
    }

    public void pesquisar() {
    }

	public ImageCacheMB getCache() {
		return cache;
	}

	public void setCache(ImageCacheMB cache) {
		this.cache = cache;
	}

	public ProjetoPesquisaService getProjetoPesquisaService() {
		return projetoPesquisaService;
	}

	public void setProjetoPesquisaService(
			ProjetoPesquisaService projetoPesquisaService) {
		this.projetoPesquisaService = projetoPesquisaService;
	}

	public ProjetoPesquisa getProjetoPesquisa() {
		return projetoPesquisa;
	}

	public void setProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
		this.projetoPesquisa = projetoPesquisa;
	}

	public DataModel<ProjetoPesquisa> getProjetosPesquisa() {
		return projetosPesquisa;
	}

	public void setProjetosPesquisa(DataModel<ProjetoPesquisa> projetosPesquisa) {
		this.projetosPesquisa = projetosPesquisa;
	}

	public Part getFotoProjetoPesquisa() {
		return fotoProjetoPesquisa;
	}

	public void setFotoProjetoPesquisa(Part fotoProjetoPesquisa) {
		this.fotoProjetoPesquisa = fotoProjetoPesquisa;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}

	public List<Pesquisador> getPesquisadores() {
		return pesquisadores;
	}

	public void setPesquisadores(List<Pesquisador> pesquisadores) {
		this.pesquisadores = pesquisadores;
	}
}