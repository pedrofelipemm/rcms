package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.AreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;

@Service("areaAtuacaoService")
@Transactional
public class AreaAtuacaoServiceImpl implements AreaAtuacaoService {

    private static final long serialVersionUID = 2895202629904115552L;

    @Autowired
    private AreaAtuacaoDAO areaDAO;

    @Override
    public void salvar(GrandeAreaAtuacao area) {
        areaDAO.salvar(area);
    }

    @Override
    public void alterar(GrandeAreaAtuacao area) {
        areaDAO.atualizar(area);
    }

    @Override
    public List<GrandeAreaAtuacao> buscarTodas() {
        return areaDAO.buscarTodos();
    }

    @Override
    public List<GrandeAreaAtuacao> buscarPorDescricao(String Descricao) {
        return areaDAO.buscarPorDescricao(Descricao);
    }

}
