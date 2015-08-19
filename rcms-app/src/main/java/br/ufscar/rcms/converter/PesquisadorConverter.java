package br.ufscar.rcms.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponse;

public abstract class PesquisadorConverter {

    public static List<PesquisadorResponse> convert(final Collection<Pesquisador> pesquisadores) {

        List<PesquisadorResponse> response = new ArrayList<PesquisadorResponse>();
        for (Pesquisador pesquisador : pesquisadores) {
            response.add(convert(pesquisador));
        }
        return response;
    }

    public static PesquisadorResponse convert(final Pesquisador pesquisador) {

        PesquisadorResponse response = new PesquisadorResponse();
        response.setCodigoLattes(pesquisador.getCodigoLattes());
        response.setEmail(pesquisador.getEmail());
        response.setEndereco(EnderecoConverter.convert(pesquisador.getEndereco()));
        response.setIdUsuario(pesquisador.getIdUsuario());
        response.setLogin(pesquisador.getLogin());
        response.setNome(pesquisador.getNome());
        response.setResumoProfissional(pesquisador.getResumoProfissional());
        response.setSenha(pesquisador.getSenha());

        return response;
    }

    public static Pesquisador convert(final PesquisadorResponse pesquisadorResponse) {

        Pesquisador pesquisador = new Pesquisador();
        pesquisador.setCodigoLattes(pesquisadorResponse.getCodigoLattes());
        pesquisador.setEmail(pesquisadorResponse.getEmail());
        pesquisador.setEndereco(EnderecoConverter.convert(pesquisadorResponse.getEndereco(), pesquisador));
        pesquisador.setIdUsuario(pesquisadorResponse.getIdUsuario());
        pesquisador.setLogin(pesquisadorResponse.getLogin());
        pesquisador.setNome(pesquisadorResponse.getNome());
        pesquisador.setResumoProfissional(pesquisadorResponse.getResumoProfissional());
        pesquisador.setSenha(pesquisadorResponse.getSenha());

        return pesquisador;
    }
}
