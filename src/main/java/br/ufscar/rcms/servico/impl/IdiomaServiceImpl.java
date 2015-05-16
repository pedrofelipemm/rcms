package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.exception.IdiomaEmUsoException;
import br.ufscar.rcms.servico.exception.IdiomaNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.util.ExceptionUtils;

@Service("idiomaService")
@Transactional(rollbackFor = RCMSException.class)
public class IdiomaServiceImpl implements IdiomaService {

    private static final long serialVersionUID = -7021575025108522991L;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Override
    public void saveOrUpdate(Idioma idioma) throws RCMSException {
        try {
            idiomaDAO.saveOrUpdate(idioma);
        } catch (DataIntegrityViolationException exception) {
            Throwable throwable = ExceptionUtils.getInnerCause(exception);
            throw new RCMSException(throwable.getMessage(), throwable);
        }
    }

    @Override
    public List<Idioma> buscarTodos() {
        return idiomaDAO.buscarTodos();
    }

    @Override
    public void remover(Idioma idioma) throws IdiomaNaoEncontradoException, IdiomaEmUsoException {

        validateRemove(idioma);
        idiomaDAO.remover(idioma);
    }

    @Override
    public Idioma buscarPorDescricao(String Descricao) {
        return idiomaDAO.buscarPorDescricao(Descricao);
    }

    private void validateRemove(Idioma idioma) throws IdiomaNaoEncontradoException, IdiomaEmUsoException {

        Idioma idiomaToRemove = idiomaDAO.buscar(idioma.getIdIdioma());
        if (idiomaToRemove == null) {
            throw new IdiomaNaoEncontradoException(idioma.getDescricao());
        }

        List<Pesquisador> pesquisadores = pesquisadorDAO.buscarTodosComIdioma(idioma.getIdIdioma());
        if (!pesquisadores.isEmpty()) {
            throw new IdiomaEmUsoException(pesquisadores);
        }
    }
}