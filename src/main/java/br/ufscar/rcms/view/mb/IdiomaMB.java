package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.IdiomaService;

@ViewScoped
@ManagedBean(name = "idiomaMB")
public class IdiomaMB extends AbstractMB {

	private static final long serialVersionUID = -7215958678138311243L;

	private IdiomaService idma;
	private transient DataModel<Idioma> idiomas;

	@ManagedProperty("#{idiomaService}")
	private IdiomaService idiomaService;

	private Idioma idioma;

	@PostConstruct
	public void inicializar() {

		setIdioma(new Idioma());
		
		Idioma idiomaEdicao = (Idioma) getFlashObject(FLASH_KEY_IDIOMA);
		if (idiomaEdicao != null) {
            idioma = idiomaEdicao;
            
		}

		idiomas =new ListDataModel<Idioma>(idiomaService.buscarTodas());
		
	}

	public IdiomaService getIdma() {
		return idma;
	}

	public void setIdma(IdiomaService idma) {
		this.idma = idma;
	}

	public DataModel<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(DataModel<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public String salvar() {

		if (getIdioma() != null) {
			Idioma idiomaExistente = (Idioma) getIdiomaService()
					.buscarPorDescricao(getIdioma().getDescricao());

			if (idiomaExistente == null) {
				getIdiomaService().salvar(getIdioma());
				// TODO i18n
				adicionarMensagemInfo("Idioma " + idioma.getDescricao()
						+ " cadastrado com sucesso!");
				idioma = new Idioma();
			}

			else {
				adicionarMensagemAlerta("Já existe um idioma com a descrição: "
						+ idioma.getDescricao());
			}

		}
		return CONSULTA_IDIOMAS;

	}

	public String excluirIdioma(Idioma idioma) {

		idiomaService.remover(idioma);

		return CONSULTA_IDIOMAS;

	}

	public String editarIdioma(Idioma idioma) {

		setFlashObject(FLASH_KEY_IDIOMA, idioma);

		return CADASTRO_IDIOMAS;
	}

	public IdiomaService getIdiomaService() {
		return idiomaService;
	}

	public void setIdiomaService(IdiomaService idiomaService) {
		this.idiomaService = idiomaService;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
}
