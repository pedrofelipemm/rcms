package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.IdiomaService;

@Service("idiomaService")
@Transactional
public class IdiomaServiceImpl implements IdiomaService {

    private static final long serialVersionUID = -7021575025108522991L;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Override
    public void salvar(Idioma idioma) {
        idiomaDAO.salvar(idioma);
    }

    @Override
    public void alterar(Idioma idioma) {
        idiomaDAO.atualizar(idioma);
    }

    @Override
    public List<Idioma> buscarTodas() {
        return idiomaDAO.buscarTodos();
    }

    @Override
    public Idioma buscarPorDescricao(String Descricao) {
        return idiomaDAO.buscarPorDescricao(Descricao);
    }

}
