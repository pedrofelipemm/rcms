package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.commons.util.FileUtils.extractFileExtension;
import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import static br.ufscar.rcms.commons.util.FileUtils.extractFileName;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.commons.util.FileUtils;
import br.ufscar.rcms.modelo.entidades.LinkMidia;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.ProjetoPesquisaService;
import br.ufscar.rcms.servico.exception.ProjetoPesquisaNaoEncontradoException;

@ViewScoped
@ManagedBean(name = "projetoPesquisaMB")
public class ProjetoPesquisaMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoPesquisaMB.class);

    @ManagedProperty("#{projetoPesquisaService}")
    private ProjetoPesquisaService projetoPesquisaService;

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    private transient List<Pesquisador> pesquisadores;

    private Pesquisador pesquisador;

    private ProjetoPesquisa projetoPesquisa;
    private transient DataModel<ProjetoPesquisa> projetosPesquisa;

    private Part imagemCarousel;

    private transient Part imagem;

    private String link;

    private List<String> imageUrls;

    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    @Override
    protected void carregarDados() {
    	link = "http://";
    	projetosPesquisa = new ListDataModel<ProjetoPesquisa>(projetoPesquisaService.buscarTodos());
    	pesquisadores = new ArrayList<Pesquisador>(pesquisadorService.buscarTodos());

        ProjetoPesquisa projetoPesquisaEdicao = (ProjetoPesquisa) getFlashObject(FLASH_KEY_PROJETO_PESQUISA);
        if (projetoPesquisaEdicao != null) {
            projetoPesquisa = projetoPesquisaService.buscarTodosDados(projetoPesquisaEdicao.getIdProjetoPesquisa());
        }
    }

    @Override
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

    public String exibir(ProjetoPesquisa projetoPesquisa) {

        projetoPesquisa = projetoPesquisaService.buscarTodosDados(projetoPesquisa.getIdProjetoPesquisa());
        if (!isEmpty(projetoPesquisa)) {
            imageUrls = projetoPesquisaService.searchGalleryUrls(projetoPesquisa.getIdProjetoPesquisa());
            setFlashObject("teste", imageUrls);
        }

        setFlashObject(FLASH_KEY_PROJETO_PESQUISA, projetoPesquisa);

        return EXIBE_PROJETO_PESQUISA;
    }

    public String editar(final ProjetoPesquisa projetoPesquisa) {

        setFlashObject(FLASH_KEY_PROJETO_PESQUISA, projetoPesquisa);

        return CADASTRO_PROJETO_PESQUISA;
    }

    public String excluir(final ProjetoPesquisa projetoPesquisa) {

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
    	setPesquisador(new Pesquisador());
    }

    public void removerPequisador(Pesquisador pesquisador) {
    	pesquisador = pesquisadorService.buscarTodosDados(pesquisador.getIdUsuario());
    	projetoPesquisa.removerPesquisador(pesquisador);
    }

    public void adicionarLink(){
    	if(!link.equals("http://")){
    		LinkMidia linkMidia = new LinkMidia();
    		linkMidia.setUrl(link);
    		linkMidia.setProjetoPesquisa(projetoPesquisa);
    		projetoPesquisa.getLinkMidia().add(linkMidia);
    		link = "http://";
    	}
    }

    public void excluirLink(final LinkMidia linkMidia){
    	if(linkMidia != null){
    		LinkMidia midia = projetoPesquisa.getLinkMidia().get(projetoPesquisa.getLinkMidia().indexOf(linkMidia));
    		projetoPesquisa.getLinkMidia().remove(midia);

    		projetoPesquisa.setLinkMidia(projetoPesquisa.getLinkMidia());

    		link = "http://";
    	}
    }

    public void pesquisar() {
    }

    public ProjetoPesquisaService getProjetoPesquisaService() {
        return projetoPesquisaService;
    }

    public void setProjetoPesquisaService(final ProjetoPesquisaService projetoPesquisaService) {
        this.projetoPesquisaService = projetoPesquisaService;
    }

    public ProjetoPesquisa getProjetoPesquisa() {
        return projetoPesquisa;
    }

    public void setProjetoPesquisa(final ProjetoPesquisa projetoPesquisa) {
        this.projetoPesquisa = projetoPesquisa;
    }

    public DataModel<ProjetoPesquisa> getProjetosPesquisa() {
        return projetosPesquisa;
    }

    public void setProjetosPesquisa(final DataModel<ProjetoPesquisa> projetosPesquisa) {
        this.projetosPesquisa = projetosPesquisa;
    }

    public Part getImagem() {
        return imagem;
    }

    public void setImagem(final Part imagem) {
        this.imagem = imagem;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(final Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public PesquisadorService getPesquisadorService() {
        return pesquisadorService;
    }

    public void setPesquisadorService(final PesquisadorService pesquisadorService) {
        this.pesquisadorService = pesquisadorService;
    }

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(final List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

	public String getLink() {
		return link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

    public Part getImagemCarousel() {
        return imagemCarousel;
    }

    public void setImagemCarousel(final Part imagemCarousel) {
        this.imagemCarousel = imagemCarousel;
    }

    public void uploadGalleryFile(final FileUploadEvent event) {

        UploadedFile file = event.getFile();
        String fileName = file.getFileName();

        TransientFile image = new TransientFile(file.getContents(), FileUtils.extractFileName(fileName),
                FileUtils.extractFileExtension(fileName));

        projetoPesquisa.addGaleria(image);
    }


    public void uploadFile(final FileUploadEvent event) {
        try {
            projetoPesquisa.getImagemCarousel().setFile(event.getFile().getContents());
            projetoPesquisa.getImagemCarousel().setFileName(extractFileName(event.getFile().getFileName()));
            projetoPesquisa.getImagemCarousel().setFileExtension(extractFileExtension(event.getFile().getFileName()));
        } catch (final Exception exception) {
            LOGGER.error(String.format("Erro ao realizar upload de imagem para o carousel: %s",
                    projetoPesquisa.getNome()), exception);
            adicionarMensagemErroByKey("erro.enviar.imagem");
        }
    }

    public List<String> getImageUrls() {
        return imageUrls != null ? imageUrls : (List<String>) getFlashObject("teste");
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

}