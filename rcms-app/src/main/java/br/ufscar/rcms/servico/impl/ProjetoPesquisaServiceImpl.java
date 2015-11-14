package br.ufscar.rcms.servico.impl;

import static br.ufscar.rcms.commons.util.FileUtils.generateGaleriaPhotoName;
import static br.ufscar.rcms.commons.util.FileUtils.generateReasearcherPhotoName;
import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.commons.util.ExceptionUtils;
import br.ufscar.rcms.dao.ProjetoPesquisaDAO;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.ProjetoPesquisaService;
import br.ufscar.rcms.servico.exception.ProjetoPesquisaNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.util.SupportedImageTypes;

@Service("projetoPesquisaService")
@Transactional(readOnly = false)
public class ProjetoPesquisaServiceImpl implements ProjetoPesquisaService {

    private static final long serialVersionUID = 4593268685421323315L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoPesquisaServiceImpl.class);

    @Autowired
    private ProjetoPesquisaDAO projetoPesquisaDAO;

    @Value("${pasta.imagens.carousel}")
    private String pastaImagensCarousel;

    @Value("${pasta.galeria.projeto}")
    private String pastaGaleriaProjetos;

    @Override
    public void salvar(final ProjetoPesquisa projetoPesquisa) {
    	projetoPesquisaDAO.salvar(projetoPesquisa);
    }

    @Override
    public ProjetoPesquisa salvarOuAtualizar(final ProjetoPesquisa projetoPesquisa) {

        try {
            ProjetoPesquisa savedProjetoPesquisa = projetoPesquisaDAO.salvarOuAtualizar(projetoPesquisa);

            if (!TransientFile.isEmpty(projetoPesquisa.getImagemCarousel())) {
                salvarImagemCarousel(projetoPesquisa);
            }

            if (!projetoPesquisa.getGaleria().isEmpty()) {
                salvarImagemGaleria(projetoPesquisa);
            }

            return savedProjetoPesquisa;
        } catch (Exception exception) {
            Throwable throwable = ExceptionUtils.getInnerCause(exception);
            throw new RuntimeException(throwable.getMessage(), throwable);
        }
    }

    private void salvarImagemGaleria(final ProjetoPesquisa projetoPesquisa) throws RCMSException {
        LOGGER.debug("BEGIN - salvarImagemGaleria() - {0}", projetoPesquisa);
        for (TransientFile image : projetoPesquisa.getGaleria()) {

            try {
                String fileName = generateGaleriaPhotoName(pastaGaleriaProjetos, projetoPesquisa.getIdProjetoPesquisa(),
                        image.getFileName(), image.getFileExtension());
                File file = new File(fileName);

                FileUtils.writeByteArrayToFile(file, image.getFile());
            } catch (IOException e) {
                String message = String.format("Erro ao salvar foto do imagem do projeto: %s",
                        projetoPesquisa.getDescricao());
                LOGGER.error(message, e);
                throw new RCMSException(message, e);
            }
        }
        LOGGER.debug("END - salvarImagemGaleria() - {0}", projetoPesquisa);
    }

    @Override
    public List<ProjetoPesquisa> buscarTodos() {
        return projetoPesquisaDAO.buscarTodos();
    }

    @Override
    public List<ProjetoPesquisa> buscarTodosOrderByNome() {
        return projetoPesquisaDAO.buscarTodosOrderByNome();
    }

    // TOTO PEDRO DELETE
    // private void carregarGaleria(final ProjetoPesquisa projetoPesquisa) {
    // try {
    //
    // final File folder = new File("/home/andre/RCMS/galeria-projetos/" +
    // projetoPesquisa.getIdProjetoPesquisa());
    // for (final File fileEntry : folder.listFiles()) {
    //
    // byte[] imagem =
    // Files.readAllBytes(Paths.get("/home/andre/RCMS/galeria-projetos/"
    // + projetoPesquisa.getIdProjetoPesquisa() + "/" + fileEntry.getName()));
    // String ext =
    // FilenameUtils.getExtension("/home/andre/RCMS/galeria-projetos/"
    // + projetoPesquisa.getIdProjetoPesquisa() + "/" + fileEntry.getName());
    // String fileName =
    // FilenameUtils.getName("/home/andre/RCMS/galeria-projetos/"
    // + projetoPesquisa.getIdProjetoPesquisa() + "/" + fileEntry.getName());
    //
    // TransientFile tf = new TransientFile();
    // tf.setFile(imagem);
    // tf.setFileExtension(ext);
    // tf.setFileLocation("/home/andre/RCMS/galeria-projetos/" +
    // projetoPesquisa.getIdProjetoPesquisa()
    // + "/");
    // tf.setFileName(fileName);
    //
    // projetoPesquisa.getGaleria().add(tf);
    // }
    //
    // } catch (IOException exception) {
    // LOGGER.error("Erro ao carregar ao carregar galeria", exception);
    // }
    // }

    @Override
    public List<String> searchGalleryUrls(final Long idProjetoPesquisa) {
        List<String> urls = new ArrayList<>();
        try {
            urls = Files.walk(Paths.get(pastaGaleriaProjetos + "/" + idProjetoPesquisa))
                    .map(e -> idProjetoPesquisa + "/" + e.getFileName()).collect(Collectors.toList());
            urls.remove(0);
        } catch (IOException exception) {
            LOGGER.error("Erro ao obter imagens para galeria", exception);
        }
        return urls;
    }

    @Override
    public void remover(final ProjetoPesquisa projetoPesquisa) throws ProjetoPesquisaNaoEncontradoException {

        ProjetoPesquisa projetoPesquisaToRemove = projetoPesquisaDAO.buscar(projetoPesquisa.getIdProjetoPesquisa());
        if (projetoPesquisaToRemove == null) {
            throw new ProjetoPesquisaNaoEncontradoException(projetoPesquisa.getIdProjetoPesquisa());
        }

        projetoPesquisaDAO.remover(projetoPesquisa);
    }

    @Override
    public void remover(final Long id) throws ProjetoPesquisaNaoEncontradoException {

        ProjetoPesquisa projetoPesquisaToRemove = projetoPesquisaDAO.buscar(id);
        if (projetoPesquisaToRemove == null) {
            throw new ProjetoPesquisaNaoEncontradoException(id);
        }

        projetoPesquisaDAO.remover(id);
    }

    @Override
    public ProjetoPesquisa buscar(final Long id) {
        return projetoPesquisaDAO.buscar(id);
    }

    @Override
    public ProjetoPesquisa buscarTodosDados(final Long idUsuario) {

        ProjetoPesquisa projetoPesquisa = projetoPesquisaDAO.buscar(idUsuario);
        lazyLoadCollections(projetoPesquisa);
        loadImagemCarousel(projetoPesquisa);
        loadImagesGallery(projetoPesquisa);

        return projetoPesquisa;
    }

    private void loadImagesGallery(ProjetoPesquisa projetoPesquisa) {
        // TODO Auto-generated method stub
    }

    private void loadImagemCarousel(final ProjetoPesquisa projetoPesquisa) {
        try {
            String fileExtension = getFileExtension(projetoPesquisa);
            if (!isEmpty(fileExtension)) {
                String fileName = projetoPesquisa.getIdProjetoPesquisa() + "." + fileExtension;
                byte[] foto = Files.readAllBytes(Paths.get(pastaImagensCarousel + fileName));

                TransientFile file = new TransientFile();
                file.setFile(foto);
                file.setFileExtension("png");
                file.setFileLocation(pastaImagensCarousel);
                file.setFileName(fileName);

                projetoPesquisa.setImagemCarousel(file);
            }
        } catch (IOException exception) {
            LOGGER.error(
                    String.format("Erro ao carregar ao carregar imagem do carousel: %s", projetoPesquisa.getNome()),
                    exception);
        }
    }

    private String getFileExtension(final ProjetoPesquisa projetoPesquisa) {
        for (SupportedImageTypes supportedImageTypes : SupportedImageTypes.values()) {
            if (Files.exists(Paths.get(pastaImagensCarousel + projetoPesquisa.getIdProjetoPesquisa() + "."
                    + supportedImageTypes.getFileType()))) {
                return supportedImageTypes.getFileType();
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public TransientFile buscarImagemCarousel(final ProjetoPesquisa projetoPesquisa) {
        ProjetoPesquisa projetoPesquisaTemp = new ProjetoPesquisa();
        if (!isEmpty(projetoPesquisa)) {
            projetoPesquisaTemp.setIdProjetoPesquisa(projetoPesquisa.getIdProjetoPesquisa());
            loadImagemCarousel(projetoPesquisaTemp);
        }
        return projetoPesquisaTemp.getImagemCarousel();
    }

    @Override
    public TransientFile buscarImagemCarousel(final Long idProjetoPesquisa) {
        return buscarImagemCarousel(buscar(idProjetoPesquisa));
    }

    private void lazyLoadCollections(final ProjetoPesquisa projetoPesquisa) {
    	projetoPesquisa.getPesquisadores().size();
    	projetoPesquisa.getLinkMidia().size();
    	projetoPesquisa.getProducoes().size();
    }

    public ProjetoPesquisaDAO getProjetoPesquisaDAO() {
        return projetoPesquisaDAO;
    }

    public void setProjetoPesquisaDAO(final ProjetoPesquisaDAO projetoPesquisaDAO) {
        this.projetoPesquisaDAO = projetoPesquisaDAO;
    }

    @Override
    public Boolean exists(final String nome) {
        return projetoPesquisaDAO.exists(nome);
    }

    @Override
    public void salvarImagemCarousel(final ProjetoPesquisa projetoPesquisa) throws RCMSException {
        LOGGER.debug("BEGIN - salvarImagemCarousel() - {0}", projetoPesquisa);
        try {

            TransientFile imagem = projetoPesquisa.getImagemCarousel();
            String fileName = generateReasearcherPhotoName(pastaImagensCarousel,
                    projetoPesquisa.getIdProjetoPesquisa().toString(), imagem.getFileExtension());
            File file = new File(fileName);

            FileUtils.writeByteArrayToFile(file, imagem.getFile());

        } catch (IOException e) {
            String message = String.format("Erro ao salvar foto para o carousel: %s", projetoPesquisa.getNome());
            LOGGER.error(message, e);
            throw new RCMSException(message, e);
        }
        LOGGER.debug("END - salvarImagemCarousel() - {0}", projetoPesquisa);
    }

    @Override
    public TransientFile buscarGaleria(final String fileName) {
        TransientFile file = new TransientFile();
        String fileExtension = getFileExtension(fileName);
        try {

            file.setFile(Files.readAllBytes(Paths.get(pastaGaleriaProjetos + fileName)));
            file.setFileExtension(fileExtension);
            file.setFileLocation(pastaGaleriaProjetos);
            file.setFileName(fileName.substring(fileName.indexOf('/') + 1));

        } catch (IOException exception) {
            LOGGER.error(String.format("Erro ao carregar ao carregar a imagem: %s", fileName), exception);
        }
        return file;
    }

    private String getFileExtension(final String fileName) {
        for (SupportedImageTypes supportedImageTypes : SupportedImageTypes.values()) {
            if (Files.exists(Paths.get(pastaGaleriaProjetos + fileName + "." + supportedImageTypes.getFileType()))) {
                return supportedImageTypes.getFileType();
            }
        }
        return StringUtils.EMPTY;
    }
}