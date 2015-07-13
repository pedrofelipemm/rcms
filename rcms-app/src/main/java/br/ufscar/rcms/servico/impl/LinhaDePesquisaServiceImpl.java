package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.LinhaDePesquisaDAO;
import br.ufscar.rcms.modelo.entidades.LinhaDePesquisa;
import br.ufscar.rcms.servico.LinhaDePesquisaService;

@Service("linhaDePesquisaService")
@Transactional
public class LinhaDePesquisaServiceImpl implements LinhaDePesquisaService {

    private static final long serialVersionUID = -3110181524489752068L;

    @Autowired
    private LinhaDePesquisaDAO linhaDePesquisaDAO;

    @Override
    public void salvar(LinhaDePesquisa linhaDePesquisa) {

        linhaDePesquisaDAO.salvar(linhaDePesquisa);
    }

    @Override
    public void salvarOuAtualizar(LinhaDePesquisa linhaDePesquisa) {

        linhaDePesquisaDAO.salvarOuAtualizar(linhaDePesquisa);
    }

    @Override
    public void remover(LinhaDePesquisa linhaDePesquisa) {

        linhaDePesquisaDAO.remover(linhaDePesquisa);
    }

    @Override
    public List<LinhaDePesquisa> buscarTodas() {

        return linhaDePesquisaDAO.buscarTodos();
    }
}
