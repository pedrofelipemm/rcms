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

        if (idiomaDAO.buscarPorDescricao(idioma.getDescricao()) == null) {
            idiomaDAO.salvarOuAtualizar(idioma);
        } else {
            // TODO PEDRO
        }
    }

    @Override
    public void alterar(Idioma idioma) {
        idiomaDAO.atualizar(idioma);
    }

    @Override
    public List<Idioma> buscarTodos() {
        return idiomaDAO.buscarTodos();
    }

    @Override
	public void remover(Idioma idioma) {
		idiomaDAO.remover(idioma);
	}

    @Override
    public Idioma buscarPorDescricao(String Descricao) {
        return idiomaDAO.buscarPorDescricao(Descricao);
    }

    public List<Idioma> listar()
    {
    return idiomaDAO.listar();
    }

//    @Override
//    public List<Idioma> buscarPorDescricao(String Descricao) {
//        return idiomaDAO.buscarPorDescricao(Descricao);
//    }

}
