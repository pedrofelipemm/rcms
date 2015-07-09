package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.ProducaoDAO;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.servico.ProducaoService;

@Service("producaoService")
@Transactional
public class ProducaoServiceImpl implements ProducaoService {

    private static final long serialVersionUID = -3551699743568411854L;

    @Autowired
    private ProducaoDAO producaoDAO;

    @Override
    public void saveOrUpdate(Producao producao) {
        producaoDAO.saveOrUpdate(producao);
    }

    @Override
    public void remove(Producao producao) {
        producaoDAO.remover(producao);
    }

    @Override
    public List<Producao> buscarTodas() {
        return producaoDAO.buscarTodos();
    }

    @Override
    public Producao buscarPorId(Long id) {
        Producao p = producaoDAO.buscar(id);
        loadLazyDependencies(p);
        return p;
    }

    @Override
    public List<ArtigoEmPeriodico> buscarArtigosEmPeriodicos(final Long idUsuario) {
        return producaoDAO.buscarArtigosEmPeriodicos(idUsuario);
    }

    private void loadLazyDependencies(Producao p) {
        p.getCitacaoBibliograficas().size();
    }

}