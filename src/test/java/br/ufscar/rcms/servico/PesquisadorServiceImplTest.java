package br.ufscar.rcms.servico;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class PesquisadorServiceImplTest extends AbstractServiceTestBase {

    @Autowired
    private PesquisadorService pesquisadorService;

    @Test
    public void salvarOuAtualizarApenasPesquisadorTest() {

        Pesquisador pesquisador = new PesquisadorBuilder("jubileu@ufscar.br", "Jubileu", "juju", "3464618468",
                "jubileu@ufscar.br", true, "Lorem Ipsum").build();

        pesquisadorService.salvarOuAtualizar(pesquisador);

        Pesquisador pesquisadorSalvo = pesquisadorService.buscar(pesquisador.getIdUsuario());
        assertNotNull(pesquisadorSalvo.getIdUsuario());

    }

    // TODO PEDRO UPDATE TEST

    @Test
    public void salvarOuAtualizarPesquisadorComEnderecoTest() {

        Endereco endereco = new Endereco();
        endereco.setEnderecoProfissional("Rua número zê");
        endereco.setEnderecoProfissionalLatitude(10);
        endereco.setEnderecoProfissionalLongitude(-20);

        Pesquisador pesquisador = new PesquisadorBuilder("jubileu@ufscar.br", "Jubileu", "juju", "3464618468",
                "jubileu@ufscar.br", true, "Lorem Ipsum").endereco(endereco).build();

        pesquisadorService.salvarOuAtualizar(pesquisador);

        Pesquisador pesquisadorSalvo = pesquisadorService.buscar(pesquisador.getIdUsuario());
        assertNotNull(pesquisadorSalvo.getEndereco().getIdEndereco());

        pesquisadorSalvo.setNome("Dunha");
        pesquisadorService.salvarOuAtualizar(pesquisadorSalvo);
        System.out.println();
    }
}