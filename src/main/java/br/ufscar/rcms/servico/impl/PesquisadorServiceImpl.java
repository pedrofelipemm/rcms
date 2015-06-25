package br.ufscar.rcms.servico.impl;

import static br.ufscar.rcms.util.FileUtils.generateReasearcherPhotoName;
import static br.ufscar.rcms.util.MiscellanyUtil.isEmpty;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.EnderecoDAO;
import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.util.ExceptionUtils;

@Service("pesquisadorService")
@Transactional(rollbackFor = { RCMSException.class })
public class PesquisadorServiceImpl implements PesquisadorService {

    private static final long serialVersionUID = 4593268685421323315L;
    private static final Logger LOGGER = LoggerFactory.getLogger(PesquisadorServiceImpl.class);

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Autowired
    private EnderecoDAO enderecoDAO;

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

            if (!isEmpty(pesquisador.getFoto())) {
                salvarFotoPesquisador(pesquisador);
            }
            return savedPesquisador;
        } catch (DataIntegrityViolationException exception) {
            Throwable throwable = ExceptionUtils.getInnerCause(exception);
            throw new RCMSException(throwable.getMessage(), throwable);
        }
    }

    private void salvarFotoPesquisador(final Pesquisador pesquisador) throws RCMSException {

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

        return pesquisador;
    }

    @Override
    public List<ArtigoEmPeriodico> buscarArtigosEmPeriodicos(final Long idUsuario) {

        return pesquisadorDAO.buscarArtigosEmPeriodicos(idUsuario);

    }

    public void lazyLoadCollections(final Pesquisador pesquisador) {

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
    }
}