package br.ufscar.rcms.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponse;

public abstract class PesquisadorConverter {

    public static List<PesquisadorResponse> convert(Collection<Pesquisador> pesquisadores) {

        List<PesquisadorResponse> response = new ArrayList<PesquisadorResponse>();
        for (Pesquisador pesquisador : pesquisadores) {
            response.add(convert(pesquisador));
        }
        return response;
    }

    private static PesquisadorResponse convert(Pesquisador pesquisador) {

        PesquisadorResponse response = new PesquisadorResponse();
        response.setCodigoLattes(pesquisador.getCodigoLattes());
        response.setEmail(pesquisador.getEmail());
        response.setEndereco(EnderecoConverter.convert(pesquisador.getEndereco()));
        response.setFlagAdministrador(pesquisador.getFlagAdministrador());
        response.setIdUsuario(pesquisador.getIdUsuario());
        response.setLogin(pesquisador.getLogin());
        response.setNome(pesquisador.getNome());
        response.setResumoProfissional(pesquisador.getResumoProfissional());
        response.setSenha(pesquisador.getSenha());

        return response;
    }
}
