package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.LinhaPesquisaDAO;
import br.ufscar.rcms.modelo.entidades.LinhaPesquisa;
import br.ufscar.rcms.servico.LinhaPesquisaService;

@Service("linhaPesquisaService")
@Transactional
public class LinhaPesquisaServiceImpl implements LinhaPesquisaService {

    private static final long serialVersionUID = -3110181524489752068L;

    @Autowired
    private LinhaPesquisaDAO linhaPesquisaDAO;

    @Override
    public void salvar(LinhaPesquisa linhaPesquisa) {

        linhaPesquisaDAO.salvar(linhaPesquisa);
    }

    @Override
    public void salvarOuAtualizar(LinhaPesquisa linhaPesquisa) {

        linhaPesquisaDAO.salvarOuAtualizar(linhaPesquisa);
    }

    @Override
    public void remover(LinhaPesquisa linhaPesquisa) {

        linhaPesquisaDAO.remover(linhaPesquisa);
    }

    @Override
    public List<LinhaPesquisa> buscarTodos() {

        return linhaPesquisaDAO.buscarTodos();
    }
}
