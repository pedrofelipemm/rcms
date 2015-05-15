package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.dao.ProjetoPesquisaDAO;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.servico.ProjetoPesquisaService;
import br.ufscar.rcms.servico.exception.ProjetoPesquisaNaoEncontradoException;
import br.ufscar.rcms.util.ExceptionUtils;

@Service("projetoPesquisaService")
@Transactional(readOnly = false)
public class ProjetoPesquisaServiceImpl implements ProjetoPesquisaService {

    private static final long serialVersionUID = 4593268685421323315L;

    @Autowired
    private ProjetoPesquisaDAO projetoPesquisaDAO;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Override
    public void salvar(ProjetoPesquisa projetoPesquisa) {
    	projetoPesquisaDAO.salvar(projetoPesquisa);
    }

    @Override
    public ProjetoPesquisa salvarOuAtualizar(ProjetoPesquisa projetoPesquisa) {

        try {
            return projetoPesquisaDAO.salvarOuAtualizar(projetoPesquisa);
        } catch (Exception e) {
            // TODO PEDRO
            throw new RuntimeException(ExceptionUtils.getInnerCause(e));
        }
    }

    @Override
    public List<ProjetoPesquisa> buscarTodos() {
        return projetoPesquisaDAO.buscarTodos();
    }

    @Override
    public List<ProjetoPesquisa> buscarTodosOrderByNome() {
        return projetoPesquisaDAO.buscarTodosOrderByNome();
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

        return projetoPesquisa;
    }
    
    private void lazyLoadCollections(ProjetoPesquisa projetoPesquisa) {
    	projetoPesquisa.getPesquisadores().size();	
    }
}