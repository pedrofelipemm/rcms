package br.ufscar.rcms.config;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import br.ufscar.rcms.modelo.entidades.Autorizacao;
import br.ufscar.rcms.servico.AutorizacaoService;

public class RcmsServletContextListener implements ApplicationListener<ContextRefreshedEvent>{
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
			ApplicationContext applicationContext = event.getApplicationContext();
			AutorizacaoService autorizacaoService = applicationContext.getBean(AutorizacaoService.class);
	//		PesquisadorService pesquisadorService = applicationContext.getBean(PesquisadorService.class);
			List<Autorizacao> autorizacoes = autorizacaoService.buscarTodos();
//			List<Pesquisador> pesquisadores = pesquisadorService.buscarTodos();
			Autorizacao admin = new Autorizacao();
			if(autorizacoes.size() == 0){
				admin = new Autorizacao();
				admin.setNomeAutorizacao("ROLE_ADMIN");
				admin.setDescricao("Administrador");
				autorizacaoService.salvar(admin);
				Autorizacao pesq = new Autorizacao();
				pesq.setNomeAutorizacao("ROLE_USER");
				pesq.setDescricao("Pesquisador");
				autorizacaoService.salvar(pesq);	

			}
//			if(pesquisadores.size() == 0){
//				Pesquisador administrador = new Pesquisador();
//				administrador.setNome("Administrador");
//				administrador.setLogin("admin");
//				administrador.setSenha("admin");
//				administrador.setEmail("admin@ufscar.com");
//				administrador.setCodigoLattes("123456789");
//				administrador.setResumoProfissional("Administrator RCMS");
//				Endereco enderecoAdmin = new Endereco();
//				enderecoAdmin.setEnderecoProfissional("CPD");
//				administrador.setEndereco(enderecoAdmin);
//				List<Autorizacao> adminAutorizacoes = autorizacaoService.buscarTodos();
//				adminAutorizacoes.add(admin);
//				administrador.setAutorizacoes(adminAutorizacoes);
//				try {
//					pesquisadorService.salvarOuAtualizar(administrador);
//				} catch (RCMSException e) {
//					e.printStackTrace();
//				}
//			}

	}

}
