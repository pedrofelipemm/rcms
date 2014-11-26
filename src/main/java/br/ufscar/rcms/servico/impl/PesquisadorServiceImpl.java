package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;

@Service("pesquisadorService")
@Transactional
public class PesquisadorServiceImpl implements PesquisadorService {

    private static final long serialVersionUID = 4593268685421323315L;

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Override
    public void salvar(Pesquisador pesquisador) {

        pesquisadorDAO.atualizar(pesquisador);
    }

    @Override
    public List<Pesquisador> buscarTodos() {
        return pesquisadorDAO.buscarTodos();
    }

    @Override
    public void remover(Pesquisador pesquisador) {

        Pesquisador pesquisadorToRemove = pesquisadorDAO.buscar(pesquisador.getIdUsuario());
        if (pesquisadorToRemove == null) {
            // TODO TRATAR EXCEPTION
            throw new RuntimeException("Pesquisador não encontrado!");
        }

        pesquisadorDAO.remover(pesquisador);
    }

}
