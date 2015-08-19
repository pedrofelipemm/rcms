package br.ufscar.rcms.servico;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.factory.EnderecoFactory;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.exception.RCMSException;

public class PesquisadorServiceTest extends AbstractServiceTestBase {

    @Autowired
    private PesquisadorService pesquisadorService;

    // TOOD PEDRO
    @Ignore
    @Test
    public void salvarOuAtualizarApenasPesquisadorTest() throws RCMSException {

        Pesquisador pesquisador = new PesquisadorBuilder("jubileu@ufscar.br", "Jubileu", "juju", "3464618468",
                "jubileu@ufscar.br", "Lorem Ipsum").build();

        pesquisadorService.salvarOuAtualizar(pesquisador);

        Pesquisador pesquisadorSalvo = pesquisadorService.buscar(pesquisador.getIdUsuario());
        assertNotNull(pesquisadorSalvo.getIdUsuario());

    }

    // TOOD PEDRO
    @Ignore
    @Test
    public void salvarOuAtualizarPesquisadorComEnderecoTest() throws RCMSException {

        Endereco endereco = EnderecoFactory.createEndereco("Rua número zê", 10D, -20D);

        Pesquisador pesquisador = new PesquisadorBuilder("jubileu@ufscar.br", "Jubileu", "juju", "3464618468",
                "jubileu@ufscar.br", "Lorem Ipsum").endereco(endereco).build();

        pesquisadorService.salvarOuAtualizar(pesquisador);

        Pesquisador pesquisadorSalvo = pesquisadorService.buscar(pesquisador.getIdUsuario());
        assertNotNull(pesquisadorSalvo.getEndereco().getIdEndereco());

        pesquisadorSalvo.setNome("Dunha");
        pesquisadorService.salvarOuAtualizar(pesquisadorSalvo);
        System.out.println();
    }
}