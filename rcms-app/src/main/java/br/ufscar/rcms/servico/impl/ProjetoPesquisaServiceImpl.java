package br.ufscar.rcms.servico.impl;

import static br.ufscar.rcms.commons.util.FileUtils.generateReasearcherPhotoName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.commons.util.ExceptionUtils;
import br.ufscar.rcms.dao.ProjetoPesquisaDAO;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.ProjetoPesquisaService;
import br.ufscar.rcms.servico.exception.ProjetoPesquisaNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;

@Service("projetoPesquisaService")
@Transactional(readOnly = false)
public class ProjetoPesquisaServiceImpl implements ProjetoPesquisaService {

    private static final long serialVersionUID = 4593268685421323315L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoPesquisaServiceImpl.class);

    @Autowired
    private ProjetoPesquisaDAO projetoPesquisaDAO;

    // @Value("${pasta.galeria.projeto}")
    // private String pastaGaleriaProjetos;

    @Override
    public void salvar(ProjetoPesquisa projetoPesquisa) {
    	projetoPesquisaDAO.salvar(projetoPesquisa);
    }

    @Override
    public ProjetoPesquisa salvarOuAtualizar(ProjetoPesquisa projetoPesquisa) {

        try {
            ProjetoPesquisa savedProjetoPesquisa = projetoPesquisaDAO.salvarOuAtualizar(projetoPesquisa);

            return savedProjetoPesquisa;
        } catch (Exception exception) {

            // TODO PEDRO
            // Throwable throwable = ExceptionUtils.getInnerCause(exception);
            // throw new RCMSException(throwable.getMessage(), throwable);

            Throwable throwable = ExceptionUtils.getInnerCause(exception);
            throw new RuntimeException(throwable.getMessage(), throwable);
        }
    }

    @Override
    public void salvarImagem(final ProjetoPesquisa projetoPesquisa, TransientFile imagem) throws RCMSException {
        // LOGGER.debug("BEGIN - salvarImagem() - {0}", projeto);
        try {
            String fileName = generateReasearcherPhotoName(
                    "/home/andre/RCMS/galeria-projetos/" + projetoPesquisa.getIdProjetoPesquisa() + "/",
                    imagem.getFileName(), imagem.getFileExtension());
            File file = new File(fileName);

            FileUtils.writeByteArrayToFile(file, imagem.getFile());

        } catch (IOException e) {
            String message = "Erro ao salvar imagem";
            // LOGGER.error(message, e);
            throw new RCMSException(message, e);
        }
        // LOGGER.debug("END - salvarImagem() - {0}", projeto);
    }

    @Override
    public List<ProjetoPesquisa> buscarTodos() {
        return projetoPesquisaDAO.buscarTodos();
    }

    @Override
    public List<ProjetoPesquisa> buscarTodosOrderByNome() {
        return projetoPesquisaDAO.buscarTodosOrderByNome();
    }

    private void carregarGaleria(final ProjetoPesquisa projetoPesquisa) {
        try {

            final File folder = new File("/home/andre/RCMS/galeria-projetos/" + projetoPesquisa.getIdProjetoPesquisa());
            for (final File fileEntry : folder.listFiles()) {
                
                byte[] imagem = Files.readAllBytes(Paths.get("/home/andre/RCMS/galeria-projetos/"
                        + projetoPesquisa.getIdProjetoPesquisa() + "/" + fileEntry.getName()));
                String ext = FilenameUtils.getExtension("/home/andre/RCMS/galeria-projetos/"
                        + projetoPesquisa.getIdProjetoPesquisa() + "/" + fileEntry.getName());
                String fileName = FilenameUtils.getName("/home/andre/RCMS/galeria-projetos/"
                        + projetoPesquisa.getIdProjetoPesquisa() + "/" + fileEntry.getName());
                
                TransientFile tf = new TransientFile();
                tf.setFile(imagem);
                tf.setFileExtension(ext);
                tf.setFileLocation("/home/andre/RCMS/galeria-projetos/" + projetoPesquisa.getIdProjetoPesquisa()
                        + "/");
                tf.setFileName(fileName);

                projetoPesquisa.getGaleria().add(tf);
            }

        } catch (IOException exception) {
            LOGGER.error("Erro ao carregar ao carregar galeria", exception);
        }
    }

    @Override
    public List<TransientFile> buscarGaleria(final Long idProjetoPesquisa) {
        return buscar(idProjetoPesquisa).getGaleria();
    }

    @Override
    public void remover(ProjetoPesquisa projetoPesquisa) throws ProjetoPesquisaNaoEncontradoException {

        ProjetoPesquisa projetoPesquisaToRemove = projetoPesquisaDAO.buscar(projetoPesquisa.getIdProjetoPesquisa());
        if (projetoPesquisaToRemove == null) {
            throw new ProjetoPesquisaNaoEncontradoException(projetoPesquisa.getIdProjetoPesquisa());
        }

        projetoPesquisaDAO.remover(projetoPesquisa);
    }

    @Override
    public void remover(Long id) throws ProjetoPesquisaNaoEncontradoException {

        ProjetoPesquisa projetoPesquisaToRemove = projetoPesquisaDAO.buscar(id);
        if (projetoPesquisaToRemove == null) {
            throw new ProjetoPesquisaNaoEncontradoException(id);
        }

        projetoPesquisaDAO.remover(id);
    }

    @Override
    public ProjetoPesquisa buscar(Long id) {
        return projetoPesquisaDAO.buscar(id);
    }

    @Override
    public ProjetoPesquisa buscarTodosDados(Long idUsuario) {

        ProjetoPesquisa projetoPesquisa = projetoPesquisaDAO.buscar(idUsuario);
        lazyLoadCollections(projetoPesquisa);
        // carregarGaleria(projetoPesquisa);

        return projetoPesquisa;
    }

    private void lazyLoadCollections(ProjetoPesquisa projetoPesquisa) {
    	projetoPesquisa.getPesquisadores().size();
    }

	public ProjetoPesquisaDAO getProjetoPesquisaDAO() {
		return projetoPesquisaDAO;
	}

	public void setProjetoPesquisaDAO(ProjetoPesquisaDAO projetoPesquisaDAO) {
		this.projetoPesquisaDAO = projetoPesquisaDAO;
	}
}