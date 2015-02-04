package br.ufscar.rcms.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.DescricaoGrupoDAO;
import br.ufscar.rcms.modelo.entidades.DescricaoGrupo;
import br.ufscar.rcms.servico.DescricaoGrupoService;

@Service("descricaoGrupoService")
@Transactional
public class DescricaoGrupoServiceImpl implements DescricaoGrupoService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private DescricaoGrupoDAO descricaoGrupoDAO;

    @Override
    public void salvar(DescricaoGrupo descricaoGrupo) {
        descricaoGrupoDAO.salvar(descricaoGrupo);
        
    }

    @Override
    public void alterar(DescricaoGrupo descricaoGrupo) {
        descricaoGrupoDAO.atualizar(descricaoGrupo);
        
    }

}
