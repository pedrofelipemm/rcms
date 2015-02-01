package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.EnderecoDAO;
import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;

@Service("pesquisadorService")
@Transactional
public class PesquisadorServiceImpl implements PesquisadorService {

    private static final long serialVersionUID = 4593268685421323315L;

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private EnderecoDAO enderecoDAO;

    @Override
    public void salvar(Pesquisador pesquisador) {
        pesquisadorDAO.salvar(pesquisador);
    }

    @Override
    public Pesquisador salvarOuAtualizar(Pesquisador pesquisador) {
        return pesquisadorDAO.salvarOuAtualizar(pesquisador);
    }

    @Override
    public List<Pesquisador> buscarTodos() {
        return pesquisadorDAO.buscarTodos();
    }

    @Override
    public void remover(Pesquisador pesquisador) {

        Pesquisador pesquisadorToRemove = pesquisadorDAO.buscar(pesquisador.getIdUsuario());
        if (pesquisadorToRemove == null) {
	    // TODO PEDRO TRATAR EXCEPTION
            throw new RuntimeException("Pesquisador n�o encontrado!");
        }

        pesquisadorDAO.remover(pesquisador);
    }

    @Override
    public Pesquisador buscar(Long id) {
        return pesquisadorDAO.buscar(id);
    }
}