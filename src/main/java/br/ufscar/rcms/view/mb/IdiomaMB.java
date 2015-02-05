package br.ufscar.rcms.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.IdiomaService;

@ViewScoped
@ManagedBean(name = "idiomaMB")
public class IdiomaMB extends AbstractMB {

	private static final long serialVersionUID = -7215958678138311243L;

	private Idioma idma;
	private transient DataModel<Idioma> idiomas;
	private List<Idioma> listaIdiomas;

	@ManagedProperty("#{idiomaService}")
	private IdiomaService idiomaService;

	private Idioma idioma;

	@PostConstruct
	public void inicializar() {

		setIdioma(new Idioma());
		// carregarDados();

	}

	public void salvar() {

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

			// idiomas = new ListDataModel<Idioma>(idma.getDescricao());

		}
	}

	// private void carregarDados() {
	// idm = idiomaService.buscarTodas();
	//
	// }

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
