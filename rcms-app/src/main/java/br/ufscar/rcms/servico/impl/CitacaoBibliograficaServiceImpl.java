package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.CitacaoBibliograficaDAO;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.servico.CitacaoBibliograficaService;

@Service("citacaoBibliograficaService")
@Transactional
public class CitacaoBibliograficaServiceImpl implements CitacaoBibliograficaService {

    private static final long serialVersionUID = 6976788858210316516L;

    @Autowired
    private CitacaoBibliograficaDAO citacaoBibliograficaDAO;

    @Override
    public void salvar(CitacaoBibliografica citacaoBibliografica) {
        citacaoBibliograficaDAO.salvar(citacaoBibliografica);
    }

    @Override
    public void remover(List<CitacaoBibliografica> citacoesBibliograficas) {
        citacaoBibliograficaDAO.remover(citacoesBibliograficas);
    }

    @Override
    public void remover(CitacaoBibliografica citacaoBibliografica) {
        citacaoBibliograficaDAO.remover(citacaoBibliografica);
    }
    
    public List<CitacaoBibliografica> buscarTodas(){
        return citacaoBibliograficaDAO.buscarTodos();
    }

    public CitacaoBibliografica buscarPorNomeCitacao(String nomeCitacao) {
        return citacaoBibliograficaDAO.buscarPorNomeCitacao(nomeCitacao);
    }

}
