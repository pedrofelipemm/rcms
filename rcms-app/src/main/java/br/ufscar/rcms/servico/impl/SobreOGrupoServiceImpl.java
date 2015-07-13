package br.ufscar.rcms.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.SobreOGrupoDAO;
import br.ufscar.rcms.modelo.entidades.SobreOGrupo;
import br.ufscar.rcms.servico.SobreOGrupoService;

@Service("sobreOGrupoService")
@Transactional
public class SobreOGrupoServiceImpl implements SobreOGrupoService {

    private static final long serialVersionUID = -7364032876876504646L;

    @Autowired
    private SobreOGrupoDAO sobreOGrupoDAO;

    @Override
    public void salvar(SobreOGrupo sobreOGrupo) {
        sobreOGrupoDAO.salvar(sobreOGrupo);

    }

    @Override
    public void alterar(SobreOGrupo sobreOGrupo) {
        sobreOGrupoDAO.atualizar(sobreOGrupo);

    }

    @Override
    public SobreOGrupo buscarPorIdioma(String idioma) {
        return sobreOGrupoDAO.buscarPorIdioma(idioma);
    }
}
