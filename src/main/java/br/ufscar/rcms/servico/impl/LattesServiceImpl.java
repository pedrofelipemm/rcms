package br.ufscar.rcms.servico.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.LattesService;

@Service("lattesService")
@Transactional
@PropertySource("classpath:application.properties")
public class LattesServiceImpl implements LattesService {

    private static final long serialVersionUID = 4593268685421323315L;
    @Value("${pasta.script.lattes}")
    private String pastaScriptLates;

    @Value("${arquivo.config.lattes}")
    private String arquivoConfig;
    
	public void executarComandoLattes(Pesquisador pesquisador)
			throws IOException {
		String hash=pesquisador.getCodigoLattes();
		String nome = pesquisador.getNome();
		criarArquivo(nome, hash, getConteudo(hash));

		try {
			new Thread(new RunScriptLattes(hash,pastaScriptLates)).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void criarArquivo(String nome, String path, String conteudo) throws IOException {
		File list = new File(pastaScriptLates + path + ".list");
		
		// if file doesnt exists, then create it
		if (!list.exists()) {
			list.createNewFile();
		}
		
		FileWriter fw = new FileWriter(list.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(path + ", " + nome);
		bw.flush();
		bw.close();
		
		File file = new File(pastaScriptLates + path + ".config");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		fw = new FileWriter(file.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(conteudo);
		bw.flush();
		bw.close();
	}

	private String getConteudo(String hash) {

		StringBuilder result = new StringBuilder("");
		result.append("global-arquivo_de_entrada  = " + hash + ".list\n");
		result.append("global-diretorio_de_saida  = " + hash + "/\n");

		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file;
		try {
			file = new File(classLoader.getResource(arquivoConfig).getPath());
			result.append(FileUtils.readFileToString(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();

	}
 
	public String getPastaScriptLates() {
		return pastaScriptLates;
	}

	public void setPastaScriptLates(String pastaScriptLates) {
		this.pastaScriptLates = pastaScriptLates;
	}

	public String getArquivoConfig() {
		return arquivoConfig;
	}

	public void setArquivoConfig(String arquivoConfig) {
		this.arquivoConfig = arquivoConfig;
	}
    
}
