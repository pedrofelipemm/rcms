package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.modelo.entidades.LinhaDePesquisa;
import br.ufscar.rcms.servico.LinhaDePesquisaService;

@Service("citacaoBibliograficaService")
@Transactional
public class CitacaoBibliograficaServiceImpl implements LinhaDePesquisaService {

    /**
     * 
     */
    private static final long serialVersionUID = 6976788858210316516L;

    @Override
    public void salvar(LinhaDePesquisa linhaPesquisa) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void salvarOuAtualizar(LinhaDePesquisa linhaPesquisa) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remover(LinhaDePesquisa linhaPesquisa) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<LinhaDePesquisa> buscarTodas() {
        // TODO Auto-generated method stub
        return null;
    }

}
