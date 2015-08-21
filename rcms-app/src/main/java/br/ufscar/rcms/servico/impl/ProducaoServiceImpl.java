package br.ufscar.rcms.servico.impl;

import static br.ufscar.rcms.commons.util.FileUtils.generateFileName;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.ProducaoDAO;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.ProducaoService;

@Service("producaoService")
@Transactional
public class ProducaoServiceImpl implements ProducaoService {

    private static final long serialVersionUID = -3551699743568411854L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducaoServiceImpl.class);

    @Autowired
    private ProducaoDAO producaoDAO;
    
    @Value("${pasta.arquivos.pdf.producao}")
    private String pastaArquivos;

    @Override
    public void saveOrUpdate(Producao producao) {
    	
        salvarArquivo(producao);
       
        producaoDAO.saveOrUpdate(producao);
    }

    private void salvarArquivo(Producao producao) {
    	try {
    		
            TransientFile arquivo = producao.getArquivoPdf();
            
            if(arquivo == null)
            	return;
            
            String fileName = generateFileName(pastaArquivos, producao.getIdProducao().toString(), arquivo.getFileExtension());
            File file = new File(fileName);
            producao.setNomePdf(producao.getIdProducao().toString() + arquivo.getFileExtension());
            

            FileUtils.writeByteArrayToFile(file, arquivo.getFile());
        } catch (IOException e) {
            String message = String.format("Erro ao salvar arquivo da produção: %s", producao.getTitulo());
            LOGGER.error(message, e);
        }
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
    public Boolean exists(String titulo, Integer ano) {
        return producaoDAO.exists(titulo, ano);
    }

    private void loadLazyDependencies(Producao p) {
        p.getAutores().size();
    }

    @Override
    public <T> List<T> buscarProducoes(Class<T> clazz) {
        return producaoDAO.buscarProducoes(clazz);
    }

    @Override
    public <T> List<T> buscarProducoes(Class<T> clazz, final Long idUsuario) {
        return producaoDAO.buscarProducoes(clazz, idUsuario);
    }
}
