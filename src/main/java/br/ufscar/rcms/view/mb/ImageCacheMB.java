package br.ufscar.rcms.view.mb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@ManagedBean(name = "imageCacheMB")
public class ImageCacheMB extends AbstractMB {

    private static final long serialVersionUID = -8022956061914668709L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageCacheMB.class);

    private static final String DEFAULT_IMAGE = "/images/person.png";

    private StreamedContent fotoPesquisador;
    private Part fotoPesquisadorPart;

    // private Map<String, StreamedContent> streamedContents = new HashMap<String, StreamedContent>();

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    @Override
    protected void limparDados() {
        sendFotoPesquisador(null);
    }

    @Override
    protected void carregarDados() {
    }

    public void sendFotoPesquisador(Part imagem) {
        fotoPesquisadorPart = imagem;

    }

    public StreamedContent getFotoPesquisador() {
        try {
            if (fotoPesquisadorPart == null || fotoPesquisadorPart.getInputStream() == null) {
                String imagePath = this.getClass().getResource(DEFAULT_IMAGE).getPath();
                fotoPesquisador = new DefaultStreamedContent(new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(new File(imagePath)))));
            } else {
                fotoPesquisador = new DefaultStreamedContent(new ByteArrayInputStream(IOUtils.toByteArray(fotoPesquisadorPart.getInputStream())));
            }
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            adicionarMensagemErro("falha.enviar.arquivo");
        }
        return fotoPesquisador;
    }

    public void setFotoPesquisador(StreamedContent fotoPesquisador) {
    }
}