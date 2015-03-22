package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.GrandeAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;

@Service("grandeAreaAtuacaoService")
@Transactional
public class GrandeAreaAtuacaoServiceImpl implements GrandeAreaAtuacaoService {

    private static final long serialVersionUID = -1179416819675785100L;

    @Autowired
    private GrandeAreaAtuacaoDAO grandeAreaDAO;

    @Override
    public void salvar(GrandeAreaAtuacao area) {
        setGrandeAreaAtuacao(area);
        grandeAreaDAO.salvar(area);
    }

    @Override
    public void alterar(GrandeAreaAtuacao area) {
        setGrandeAreaAtuacao(area);
        grandeAreaDAO.atualizar(area);
    }

    @Override
    public List<GrandeAreaAtuacao> buscarTodas() {
        return grandeAreaDAO.buscarTodos();
    }

    @Override
    public List<GrandeAreaAtuacao> buscarPorDescricao(String Descricao) {
        return grandeAreaDAO.buscarPorDescricao(Descricao);
    }

    @Override
    public void remover(GrandeAreaAtuacao area) {
        grandeAreaDAO.remover(area);
    }

    private void setGrandeAreaAtuacao(GrandeAreaAtuacao grandeAreaAtuacao) {
        for (AreaAtuacao areaAtuacao : grandeAreaAtuacao.getAreasDeAtuacao()) {
            areaAtuacao.setGrandeAreaAtuacao(grandeAreaAtuacao);
        }
    }
}