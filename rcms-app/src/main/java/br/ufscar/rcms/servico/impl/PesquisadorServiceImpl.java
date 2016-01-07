package br.ufscar.rcms.servico.impl;
import static br.ufscar.rcms.commons.util.FileUtils.generateReasearcherPhotoName;
import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.commons.util.ExceptionUtils;
import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.util.SupportedImageTypes;

@Service("pesquisadorService")
@Transactional(rollbackFor = { RCMSException.class })
public class PesquisadorServiceImpl implements PesquisadorService {

    private static final long serialVersionUID = 4593268685421323315L;
    private static final Logger LOGGER = LoggerFactory.getLogger(PesquisadorServiceImpl.class);

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Value("${pasta.script.foto.pesquisador}")
    private String pastaFotosPesquisadores;

    @Override
    public Pesquisador salvarOuAtualizar(final Pesquisador pesquisador) throws RCMSException {
        try {
            pesquisador.getEndereco().setPesquisador(pesquisador);

            for (CompreensaoIdioma compreensaoIdioma : pesquisador.getCompreensaoIdiomas()) {
                if (compreensaoIdioma.getCompreensaoIdiomaPK() != null
                        && compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma() != null
                        && compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma().getIdIdioma() == null) {
                    idiomaDAO.saveOrUpdate(compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma());
                }
            }

            Pesquisador savedPesquisador = pesquisadorDAO.salvarOuAtualizar(pesquisador);

            if (!TransientFile.isEmpty(pesquisador.getFoto())) {
                salvarFotoPesquisador(pesquisador);
            }
            return savedPesquisador;
        } catch (DataIntegrityViolationException exception) {
            Throwable throwable = ExceptionUtils.getInnerCause(exception);
            throw new RCMSException(throwable.getMessage(), throwable);
        }
    }

    @Override
    public void salvarFotoPesquisador(final Pesquisador pesquisador) throws RCMSException {
        LOGGER.debug("BEGIN - salvarFotoPesquisador() - {0}", pesquisador);
        try {

            TransientFile foto = pesquisador.getFoto();
            String fileName = generateReasearcherPhotoName(pastaFotosPesquisadores, pesquisador.getCodigoLattes(), foto.getFileExtension());
            File file = new File(fileName);

            FileUtils.writeByteArrayToFile(file, foto.getFile());
        } catch (IOException e) {
            String message = String.format("Erro ao salvar foto do pesquisador: %s", pesquisador.getNome());
            LOGGER.error(message, e);
            throw new RCMSException(message, e);
        }
        LOGGER.debug("END - salvarFotoPesquisador() - {0}", pesquisador);
    }

    @Override
    public List<Pesquisador> buscarTodos() {
        return pesquisadorDAO.buscarTodos();
    }

    @Override
    public List<Pesquisador> buscarTodosComIdioma(final Long idIdioma) {
        return pesquisadorDAO.buscarTodosComIdioma(idIdioma);
    }

    @Override
    public List<Pesquisador> buscarTodosOrderByNome() {
        return pesquisadorDAO.buscarTodosOrderByNome();
    }

    @Override
    public void remover(final Pesquisador pesquisador) throws PesquisadorNaoEncontradoException {

        Pesquisador pesquisadorToRemove = pesquisadorDAO.buscar(pesquisador.getIdUsuario());
        if (pesquisadorToRemove == null) {
            throw new PesquisadorNaoEncontradoException(pesquisador.getIdUsuario());
        }

        pesquisadorDAO.remover(pesquisador);
    }

    @Override
    public void remover(final Long id) throws PesquisadorNaoEncontradoException {

        Pesquisador pesquisadorToRemove = pesquisadorDAO.buscar(id);
        if (pesquisadorToRemove == null) {
            throw new PesquisadorNaoEncontradoException(id);
        }

        pesquisadorDAO.remover(id);
    }

    @Override
    public Pesquisador buscar(final Long id) {
        return pesquisadorDAO.buscar(id);
    }

    @Override
    public Pesquisador buscarTodosDados(final Long idUsuario) {

        Pesquisador pesquisador = pesquisadorDAO.buscar(idUsuario);
        lazyLoadCollections(pesquisador);
        loadPhoto(pesquisador);

        return pesquisador;
    }

    @Override
    public TransientFile buscarFoto(final Pesquisador pesquisador) {
        Pesquisador pesquisadorTemp = new Pesquisador();
        if (!isEmpty(pesquisador)) {
            pesquisadorTemp.setCodigoLattes(pesquisador.getCodigoLattes());
            loadPhoto(pesquisadorTemp);
        }
        return pesquisadorTemp.getFoto();
    }

    @Override
    public TransientFile buscarFoto(final Long idUsuario) {
        return buscarFoto(buscar(idUsuario));
    }

    @Override
    public Pesquisador buscarPorLogin(final String login) {
        return pesquisadorDAO.buscarPorLogin(login);
    }

    @Override
    public void saveOrUpdate(final Pesquisador pesquisador) {
        pesquisadorDAO.saveOrUpdate(pesquisador);
    }

    private void loadPhoto(final Pesquisador pesquisador) {
        try {
            String fileExtension = getFileExtension(pesquisador);
            if (!isEmpty(fileExtension)) {
                String fileName = pesquisador.getCodigoLattes() + "." + fileExtension;
                byte[] foto = Files.readAllBytes(Paths.get(pastaFotosPesquisadores + fileName));

                TransientFile file = new TransientFile();
                file.setFile(foto);
                file.setFileExtension("png");
                file.setFileLocation(pastaFotosPesquisadores);
                file.setFileName(fileName);

                pesquisador.setFoto(file);
            }
        } catch (IOException exception) {
            LOGGER.error(String.format("Erro ao carregar ao carregar foto do pesquisador: %s", pesquisador.getNome()), exception);
        }
    }

    private String getFileExtension(final Pesquisador pesquisador) {
        for (SupportedImageTypes supportedImageTypes : SupportedImageTypes.values()) {
            if (Files.exists(Paths.get(pastaFotosPesquisadores + pesquisador.getCodigoLattes() + "." + supportedImageTypes.getFileType()))) {
                return supportedImageTypes.getFileType();
            }
        }
        return StringUtils.EMPTY;
    }

    private void lazyLoadCollections(final Pesquisador pesquisador) {
        pesquisador.getAreaAtuacoes().size();
        pesquisador.getCitacaoBibliograficas().size();
        pesquisador.getCompreensaoIdiomas().size();
        pesquisador.getFormacoes().size();
        pesquisador.getOrganizacaoEventos().size();
        pesquisador.getOrientacoes().size();
        pesquisador.getParticipacaoEventos().size();
        pesquisador.getLinhasDePesquisa().size();
        pesquisador.getParticipacaoEventos().size();
        pesquisador.getPremios().size();
        pesquisador.getProjetosPesquisa().size();
        pesquisador.getAutorizacoes().size();
    }
}