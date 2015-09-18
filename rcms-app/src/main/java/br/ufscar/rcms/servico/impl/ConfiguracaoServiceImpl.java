package br.ufscar.rcms.servico.impl;

import static br.ufscar.rcms.commons.util.FileUtils.generateReasearcherPhotoName;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.ufscar.rcms.dao.ConfiguracaoDAO;
import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoSistema;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.ConfiguracaoService;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.view.mb.ConfigSistemaMB;

@Service("configuracaoService")
@Transactional
public class ConfiguracaoServiceImpl implements ConfiguracaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSistemaMB.class);
	
    @Autowired
    private ConfiguracaoDAO configuracaoDAO;
    
    @Value("${pasta.logotipo}")
    private String pastaLogotipo;

    @Override
    public Configuracao saveOrUpdate(final Configuracao configuracao) {
        return configuracaoDAO.saveOrUpdate(configuracao);
    }

    @Override
    public List<Configuracao> buscarPorTipo(final Tipo... tipos) {
        return configuracaoDAO.buscarPorTipo(tipos);
    }
    
    @Override
    public boolean verificaConfiguracao(final Tipo... tipos) {
        List<Configuracao> listaConfigs = configuracaoDAO.buscarPorTipo(tipos);
        if(listaConfigs == null || listaConfigs.size() != tipos.length){
        	return false;
        }
        return true;
    }

    @Override
    public ConfiguracaoSistema buscarPorKey(Tipo tipo) {
        return configuracaoDAO.buscarPorKey(tipo);
    }

    @Override
    public ConfiguracaoIndice buscarPorIdETipo(Long id, Tipo tipo) {
        return configuracaoDAO.buscarPorIdETipo(id, tipo);
    }

    @Override
    public void remover(Configuracao configuracao) {
        configuracaoDAO.remover(configuracao);
    }
    
    @Override
    public String salvarLogotipo(TransientFile foto) throws RCMSException {
        LOGGER.debug("BEGIN - salvarLogotipo()");
        try {
            String fileName = generateReasearcherPhotoName(pastaLogotipo, "logotipo", foto.getFileExtension());
            File file = new File(fileName);

            FileUtils.writeByteArrayToFile(file, foto.getFile());
            LOGGER.debug("END - salvarLogotipo()");
            return fileName;
        } catch (IOException e) {
            String message = String.format("Erro ao salvar logotipo");
            LOGGER.error(message, e);
            throw new RCMSException(message, e);
        }
    }
}