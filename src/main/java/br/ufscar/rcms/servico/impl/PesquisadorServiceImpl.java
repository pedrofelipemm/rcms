package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.util.ExceptionUtils;

@Service("pesquisadorService")
@Transactional(rollbackFor = RCMSException.class)
public class PesquisadorServiceImpl implements PesquisadorService {

    private static final long serialVersionUID = 4593268685421323315L;

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Override
    public void salvar(Pesquisador pesquisador) {
        pesquisadorDAO.salvar(pesquisador);
    }

    @Override
    // TODO PEDRO save or update
    public Pesquisador salvarOuAtualizar(Pesquisador pesquisador) {
        try {
        pesquisador.getEndereco().setPesquisador(pesquisador);

            // TODO PEDRO VALIDATE
        for (CompreensaoIdioma compreensaoIdioma : pesquisador.getCompreensaoIdiomas()) {
            if (compreensaoIdioma.getCompreensaoIdiomaPK() != null
                    && compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma() != null
                    && compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma().getIdIdioma() == null) {
                    idiomaDAO.saveOrUpdate(compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma());
            }
        }


            return pesquisadorDAO.salvarOuAtualizar(pesquisador);
        } catch (DataIntegrityViolationException exception) {
            Throwable throwable = ExceptionUtils.getInnerCause(exception);
            // TODO PEDRO
            // throw new RCMSException(throwable.getMessage(), throwable);
            throw new RuntimeException(throwable.getMessage(), throwable);
        }
    }

    @Override
    public List<Pesquisador> buscarTodos() {
        return pesquisadorDAO.buscarTodos();
    }

    @Override
    public List<Pesquisador> buscarTodosComIdioma(Long idIdioma) {
        return pesquisadorDAO.buscarTodosComIdioma(idIdioma);
    }

    @Override
    public List<Pesquisador> buscarTodosOrderByNome() {
        return pesquisadorDAO.buscarTodosOrderByNome();
    }

    @Override
    public void remover(Pesquisador pesquisador) throws PesquisadorNaoEncontradoException {

        Pesquisador pesquisadorToRemove = pesquisadorDAO.buscar(pesquisador.getIdUsuario());
        if (pesquisadorToRemove == null) {
            throw new PesquisadorNaoEncontradoException(pesquisador.getIdUsuario());
        }

        pesquisadorDAO.remover(pesquisador);
    }

    @Override
    public void remover(Long id) throws PesquisadorNaoEncontradoException {

        Pesquisador pesquisadorToRemove = pesquisadorDAO.buscar(id);
        if (pesquisadorToRemove == null) {
            throw new PesquisadorNaoEncontradoException(id);
        }

        pesquisadorDAO.remover(id);
    }

    @Override
    public Pesquisador buscar(Long id) {
        return pesquisadorDAO.buscar(id);
    }

    @Override
    public Pesquisador buscarTodosDados(Long idUsuario) {

        Pesquisador pesquisador = pesquisadorDAO.buscar(idUsuario);
        lazyLoadCollections(pesquisador);

        return pesquisador;
    }

    private void lazyLoadCollections(Pesquisador pesquisador) {

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