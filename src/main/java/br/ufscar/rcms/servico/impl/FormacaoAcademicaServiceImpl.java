package br.ufscar.rcms.servico.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.rcms.dao.FormacaoAcademicaDAO;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.servico.FormacaoAcademicaService;

@Service("formacaoAcademica")
@Transactional
public class FormacaoAcademicaServiceImpl implements FormacaoAcademicaService{

	private static final long serialVersionUID = 1L;

	@Autowired
	private FormacaoAcademicaDAO formacaoAcademicaDao;

	@Override
	public void salvar(FormacaoAcademica formacaoAcademica) {
		formacaoAcademicaDao.salvar(formacaoAcademica);
	}

	public List<FormacaoAcademica> buscarTodos(){
		return formacaoAcademicaDao.buscarTodos();
	}

    @Override
    public List<FormacaoAcademica> buscarFormacaoAcademica(Long idPesquisador) {
	    return formacaoAcademicaDao.buscarFormacaoAcademica(idPesquisador);
	}
}