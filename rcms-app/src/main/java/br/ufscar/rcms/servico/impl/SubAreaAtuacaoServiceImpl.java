package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.SubAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.SubAreaAtuacaoService;

@Service("subAreaAtuacaoService")
@Transactional
public class SubAreaAtuacaoServiceImpl implements SubAreaAtuacaoService {

    private static final long serialVersionUID = 6385315929837094245L;

    @Autowired
    private SubAreaAtuacaoDAO subAreaDAO;

    @Override
    public void salvar(SubAreaAtuacao subArea) {
        setSubAreaAtuacao(subArea);
        subAreaDAO.salvar(subArea);
    }

    @Override
    public void alterar(SubAreaAtuacao subArea) {
        setSubAreaAtuacao(subArea);
        subAreaDAO.atualizar(subArea);
    }

    @Override
    public void remover(SubAreaAtuacao subArea) {
        subAreaDAO.remover(subArea);
    }

    @Override
    public List<SubAreaAtuacao> buscarTodas() {
        return subAreaDAO.buscarTodos();
    }

    @Override
    public List<SubAreaAtuacao> buscarPorDescricao(String Descricao) {
        return subAreaDAO.buscarPorDescricao(Descricao);
    }

    private void setSubAreaAtuacao(SubAreaAtuacao subAreaAtuacao) {
        for (EspecializacaoAreaAtuacao especializacaoAreaAtuacao : subAreaAtuacao.getEspecializacoes()) {
            especializacaoAreaAtuacao.setSubAreaAtuacao(subAreaAtuacao);
        }
    }
}