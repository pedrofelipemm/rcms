package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.GrandeAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.util.ExceptionUtils;

@Service("grandeAreaAtuacaoService")
@Transactional
public class GrandeAreaAtuacaoServiceImpl implements GrandeAreaAtuacaoService {

    private static final long serialVersionUID = -1179416819675785100L;

    @Autowired
    private GrandeAreaAtuacaoDAO grandeAreaDAO;

    @Override
    public void salvar(final GrandeAreaAtuacao area) {
        setGrandeAreaAtuacao(area);
        grandeAreaDAO.salvar(area);
    }

    @Override
    public void saveOrUpdate(final GrandeAreaAtuacao grandeAreaAtuacao) throws RCMSException {
        try {
            grandeAreaDAO.saveOrUpdate(grandeAreaAtuacao);
        } catch (final DataIntegrityViolationException exception) {
            final Throwable throwable = ExceptionUtils.getInnerCause(exception);
            throw new RCMSException(throwable.getMessage(), throwable);
        }
    }

    @Override
    public void alterar(final GrandeAreaAtuacao area) {
        setGrandeAreaAtuacao(area);
        grandeAreaDAO.atualizar(area);
    }

    @Override
    public List<GrandeAreaAtuacao> buscarTodas() {
        return grandeAreaDAO.buscarTodos();
    }

    @Override
    public List<GrandeAreaAtuacao> buscarPorDescricao(final String Descricao) {
        return grandeAreaDAO.buscarPorDescricao(Descricao);
    }

    @Override
    public void remover(final GrandeAreaAtuacao area) {
        grandeAreaDAO.remover(area);
    }

    private void setGrandeAreaAtuacao(final GrandeAreaAtuacao grandeAreaAtuacao) {
        for (final AreaAtuacao areaAtuacao : grandeAreaAtuacao.getAreasDeAtuacao()) {
            areaAtuacao.setGrandeAreaAtuacao(grandeAreaAtuacao);
        }
    }
}