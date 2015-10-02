package br.ufscar.rcms.servico.impl;

import static br.ufscar.rcms.commons.util.FileUtils.generateFileName;
import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.ProducaoDAO;
import br.ufscar.rcms.modelo.entidades.AutorProducao;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.ProducaoService;

@Service("producaoService")
@Transactional
public class ProducaoServiceImpl implements ProducaoService {

    private static final long serialVersionUID = -3551699743568411854L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducaoServiceImpl.class);

    @Autowired
    private ProducaoDAO producaoDAO;

    @Value("${pasta.arquivos.pdf.producao}")
    private String pastaArquivos;

    @Override
    public void saveOrUpdate(final Producao producao) {

        salvarArquivo(producao);

        for (AutorProducao autor : producao.getAutores()) {
            autor.setProducao(producao);
        }

        producaoDAO.saveOrUpdate(producao);
    }

    private void salvarArquivo(final Producao producao) {
        try {

            TransientFile arquivo = producao.getArquivoPdf();

            if (!isEmpty(producao.getIdProducao()) && !TransientFile.isEmpty(arquivo)) {

                String fileName = generateFileName(pastaArquivos, producao.getIdProducao().toString(),
                        arquivo.getFileExtension());
                File file = new File(fileName);
                producao.setNomePdf(producao.getIdProducao().toString() + arquivo.getFileExtension());

                FileUtils.writeByteArrayToFile(file, arquivo.getFile());
            }
        } catch (IOException e) {
            String message = String.format("Erro ao salvar arquivo da produção: %s", producao.getTitulo());
            LOGGER.error(message, e);
        }
    }

	@Override
    public void remove(final Producao producao) {
        producaoDAO.remover(producao);
    }

    @Override
    public List<Producao> buscarTodas() {
        return producaoDAO.buscarTodos();
    }

    @Override
    public Producao buscarPorId(final Long id) {
        Producao p = producaoDAO.buscar(id);
        loadLazyDependencies(p);
        return p;
    }

    @Override
    public Boolean exists(final String titulo, final Integer ano) {
        return producaoDAO.exists(titulo, ano);
    }

    private void loadLazyDependencies(final Producao p) {
        p.getAutores().size();
    }

    @Override
    public <T> List<T> buscarProducoes(final Class<T> clazz) {
        return producaoDAO.buscarProducoes(clazz);
    }

    @Override
    public <T> List<T> buscarProducoes(final Class<T> clazz, final Long idUsuario) {
        return producaoDAO.buscarProducoes(clazz, idUsuario);
    }

    @Override
    public TransientFile buscarPdf(final Long producaoId) {
        return buscarPdf(buscarPorId(producaoId));
    }

    @Override
    public TransientFile buscarPdf(final Producao producao) {
        if (!isEmpty(producao)) {
            loadPdf(producao);
        }
        return producao.getArquivoPdf();
    }

    private void loadPdf(final Producao producao) {
        try {
            String fileExtension = "pdf";
            if (!isEmpty(fileExtension)) {
                String fileName = producao.getIdProducao() + "." + fileExtension;
                byte[] pdf = Files.readAllBytes(Paths.get(pastaArquivos + fileName));

                TransientFile file = new TransientFile();
                file.setFile(pdf);
                file.setFileExtension("pdf");
                file.setFileLocation(pastaArquivos);
                file.setFileName(fileName);

                producao.setArquivoPdf(file);
            }
        } catch (IOException exception) {
            LOGGER.error(String.format("Erro ao carregar ao carregar o pdf da produção: %s", producao.getTitulo()),
                    exception);
        }
    }

    @Override
    public StreamedContent loadPDF(final Producao p) {
        String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
        InputStream stream;
        try {
            stream = new FileInputStream(pastaArquivos + p.getIdProducao() + ".pdf");
            return new DefaultStreamedContent(stream, "application/pdf", "producao_" + p.getIdProducao().toString()
                    + ".pdf");
        } catch (FileNotFoundException e) {
            LOGGER.error("Erro ao carregar PDF", e);
        }
        return null;
    }
    
    @Override
    public Producao buscarTodosDados(final long producaoId){
    	Producao p = producaoDAO.buscar(producaoId);
    	loadLazyCollections(p);
    	
    	return p;
    }

	private void loadLazyCollections(Producao p) {
		p.getAutores().size();		
	}
}
