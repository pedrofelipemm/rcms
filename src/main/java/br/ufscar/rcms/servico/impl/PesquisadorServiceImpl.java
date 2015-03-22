package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;

@Service("pesquisadorService")
@Transactional
public class PesquisadorServiceImpl implements PesquisadorService {

    private static final long serialVersionUID = 4593268685421323315L;

    @Autowired
    private PesquisadorDAO pesquisadorDAO;

    @Autowired
    private IdiomaDAO idiomaDAO;

    @Override
    public void salvar(Pesquisador pesquisador) {
        pesquisadorDAO.salvar(pesquisador);
    }

    @Override
    public Pesquisador salvarOuAtualizar(Pesquisador pesquisador) {

        pesquisador.getEndereco().setPesquisador(pesquisador);

        for (CompreensaoIdioma compreensaoIdioma : pesquisador.getCompreensaoIdiomas()) {
            if (compreensaoIdioma.getCompreensaoIdiomaPK() != null
                    && compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma() != null
                    && compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma().getIdIdioma() == null) {
                idiomaDAO.salvar(compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma());
            }
        }

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
            throw new RuntimeException("Pesquisador não encontrado!");
        }

        pesquisadorDAO.remover(pesquisador);
    }

    @Override
    public Pesquisador buscar(Long id) {
        return pesquisadorDAO.buscar(id);
    }

    @Override
    public Pesquisador buscarTodosDados(Long idUsuario) {

        Pesquisador pesquisador = pesquisadorDAO.buscar(idUsuario);
        lazyLoadCollections(pesquisador);

        return pesquisador;
    }

    private void lazyLoadCollections(Pesquisador pesquisador) {

        pesquisador.getAreaAtuacoes().size();
        pesquisador.getCitacaoBibliograficas().size();
        pesquisador.getCompreensaoIdiomas().size();
        pesquisador.getFormacoes().size();
        pesquisador.getOrganizacaoEventos().size();
        pesquisador.getOrientacoes().size();
        pesquisador.getParticipacaoEventos().size();
        pesquisador.getPesquisadorLinhaPesquisa().size();
        pesquisador.getPremios().size();
        pesquisador.getProjetosPesquisa().size();
    }
}