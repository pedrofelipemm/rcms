package br.ufscar.rcms.builder;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.Validate;

import br.ufscar.rcms.factory.EnderecoFactory;
import br.ufscar.rcms.factory.FormacaoAcademicaFactory;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.lattes.EnderecoLattes;
import br.ufscar.rcms.modelo.lattes.FormacaoLattes;
import br.ufscar.rcms.modelo.lattes.FormacoesAcademicaLattes;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;

public class PesquisadorBuilder implements Builder<Pesquisador> {

    private Pesquisador pesquisador;

    public PesquisadorBuilder(String login, String nome, String senha, String codigoLattes, String email,
            boolean flagAdministrador, String resumoProfissional) {
        this(null, login, nome, senha, codigoLattes, email, flagAdministrador, resumoProfissional);
    }

    public PesquisadorBuilder(Long idUsuario, String login, String nome, String senha, String codigoLattes,
            String email, boolean flagAdministrador, String resumoProfissional) {

        pesquisador = new Pesquisador();
        pesquisador.setIdUsuario(idUsuario);
        pesquisador.setLogin(login);
        pesquisador.setNome(nome);
        pesquisador.setSenha(senha);
        pesquisador.setCodigoLattes(codigoLattes);
        pesquisador.setEmail(email);
        pesquisador.setFlagAdministrador(flagAdministrador);
        pesquisador.setResumoProfissional(resumoProfissional);

        validatePesquisador(pesquisador);
    }

    public PesquisadorBuilder(PesquisadorLattes pesquisadorLattes, Pesquisador pesquisador) {

        this(pesquisador.getIdUsuario(), pesquisador.getLogin(), pesquisadorLattes.getIdentificacao().getNomeCompleto(),
                pesquisador.getSenha(), pesquisador.getCodigoLattes(), pesquisador.getEmail(),
                pesquisador.getFlagAdministrador(), pesquisador.getResumoProfissional());

        validatePesquisador(pesquisadorLattes, pesquisador);

        endereco(pesquisadorLattes.getEndereco(), pesquisador);
        formacaoAcademica(pesquisadorLattes.getFormacoes(), pesquisador);
    }

    public PesquisadorBuilder endereco(EnderecoLattes endereco, Pesquisador pesquisador) {

        if (pesquisador.getEndereco() != null) {
            this.pesquisador.setEndereco(EnderecoFactory.createEndereco(pesquisador.getEndereco().getIdEndereco(),
                    endereco.getEnderecoProfissional(), endereco.getEnderecoProfissionalLatitude(),
                    endereco.getEnderecoProfissionalLongitude()));
        } else {
            this.pesquisador.setEndereco(EnderecoFactory.createEndereco(endereco.getEnderecoProfissional(),
                    endereco.getEnderecoProfissionalLatitude(), endereco.getEnderecoProfissionalLongitude()));
        }

        return this;
    }

    public PesquisadorBuilder endereco(Endereco endereco) {

        pesquisador.setEndereco(endereco);
        return this;
    }

    public PesquisadorBuilder formacaoAcademica(FormacaoAcademica... formacao) {

        pesquisador.setFormacoes(Arrays.asList(formacao));
        return this;
    }

    public PesquisadorBuilder formacaoAcademica(List<FormacaoAcademica> formacoes) {

        pesquisador.setFormacoes(formacoes);
        return this;
    }

    public PesquisadorBuilder formacaoAcademica(FormacoesAcademicaLattes formacoes, Pesquisador pesquisador) {

        // TODO PEDRO
        for (FormacaoLattes formacao : formacoes.getFormacoes()) {

            this.pesquisador.getFormacoes().add(FormacaoAcademicaFactory.createFormacaoAcademica(
                    formacao.getAnoConclusao(), formacao.getAnoInicio(), formacao.getDescricao(),
                    formacao.getNomeInstituicao(), formacao.getTipo(), pesquisador));
        }

        return this;
    }

    @Override
    public Pesquisador build() {
        return pesquisador;
    }

    private void validatePesquisador(Pesquisador pesquisador) {
        Validate.notBlank(pesquisador.getLogin());
        Validate.notBlank(pesquisador.getNome());
        Validate.notBlank(pesquisador.getSenha());
        Validate.notBlank(pesquisador.getCodigoLattes());
        Validate.notBlank(pesquisador.getEmail());
        Validate.notBlank(pesquisador.getResumoProfissional());
        Validate.notNull(pesquisador.getFlagAdministrador());
    }

    private void validatePesquisador(PesquisadorLattes pesquisadorLattes, Pesquisador pesquisador) {

        validatePesquisador(pesquisador);

        if (!pesquisador.getCodigoLattes().equals(pesquisadorLattes.getCodigoLattes())) {
            throw new IllegalStateException("Código lattes dos pequisadores não são iguais!");
        }
    }
}