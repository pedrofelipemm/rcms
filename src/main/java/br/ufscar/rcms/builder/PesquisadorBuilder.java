package br.ufscar.rcms.builder;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.Validate;

import br.ufscar.rcms.factory.EnderecoFactory;
import br.ufscar.rcms.factory.FormacaoAcademicaFactory;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.ParticipacaoEvento;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.PremioTitulo;
import br.ufscar.rcms.modelo.lattes.AreaAtuacaoLattes;
import br.ufscar.rcms.modelo.lattes.EnderecoLattes;
import br.ufscar.rcms.modelo.lattes.EventoLatttes;
import br.ufscar.rcms.modelo.lattes.FormacaoLattes;
import br.ufscar.rcms.modelo.lattes.FormacoesAcademicaLattes;
import br.ufscar.rcms.modelo.lattes.IdentificacaoLattes;
import br.ufscar.rcms.modelo.lattes.IdiomasLattes;
import br.ufscar.rcms.modelo.lattes.OrganizacaoEventoLattes;
import br.ufscar.rcms.modelo.lattes.ParticipacaoEventoLattes;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.modelo.lattes.PremioLattes;
import br.ufscar.rcms.modelo.lattes.PremiosLattes;
import br.ufscar.rcms.modelo.lattes.ProjetetosPesquisaLattes;

public class PesquisadorBuilder implements Builder<Pesquisador> {

    private Pesquisador pesquisador;
    private Pesquisador cachedPesquisador;

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

        this(pesquisador.getIdUsuario(), pesquisador.getLogin(),
                pesquisadorLattes.getIdentificacao().getNomeCompleto(), pesquisador.getSenha(), pesquisador
                        .getCodigoLattes(), pesquisador.getEmail(), pesquisador.getFlagAdministrador(), pesquisador
                        .getResumoProfissional());

        validatePesquisador(pesquisadorLattes, pesquisador);
        cachedPesquisador = pesquisador;
//        organizacaoEventos(pesquisadorLattes.getOrganizacaoEvento(), pesquisador);
//        orientacoes(pesquisadorLattes, pesquisador);
//        areaAtuacoes(pesquisadorLattes.getAreaAtuacao(), pesquisador);
//        projetosPesquisa(pesquisadorLattes.getProjetosPesquisa(), pesquisador);
    }

    public PesquisadorBuilder projetosPesquisa(ProjetetosPesquisaLattes projetosPesquisa) {
        // TODO Pedro
        return this;
    }

    public PesquisadorBuilder areaAtuacoes(AreaAtuacaoLattes areaAtuacao, Pesquisador pesquisador) {
        // TODO Pedro
        return this;
    }

    public PesquisadorBuilder orientacoes(PesquisadorLattes pesquisadorLattes, Pesquisador pesquisador) {
        // TODO Pedro
        return this;
    }

    public PesquisadorBuilder organizacaoEventos(OrganizacaoEventoLattes organizacaoEvento, Pesquisador pesquisador) {
        // TODO Pedro
        return this;
    }

    public PesquisadorBuilder participacaoEventos(ParticipacaoEventoLattes participacaoEvento) {
        for (EventoLatttes eventoLatttes : participacaoEvento.getEventos()) {
            pesquisador.addParticipacaoEventos(new ParticipacaoEvento(cachedPesquisador, eventoLatttes.getTitulo(), eventoLatttes.getAno()));
        }
        return this;
    }

    public PesquisadorBuilder premios(PremiosLattes premios) {
        for (PremioLattes premioLattes : premios.getPremio()) {
            pesquisador.addPremios(new PremioTitulo(cachedPesquisador, premioLattes.getAno(), premioLattes.getDescricao()));
        }
        return this;
    }

    public PesquisadorBuilder compreensaoIdiomas(IdiomasLattes idiomas, Pesquisador pesquisador) {
        // TODO Pedro
        return this;
    }

    public PesquisadorBuilder citacaoBibliografica(IdentificacaoLattes identificacao) {
        if (identificacao != null) {
            String[] citacoes = identificacao.getNomeCitacaoBibliografica().split(";");
            for (String citacao : citacoes) {
                pesquisador.addCitacaoBibliograficas(new CitacaoBibliografica(cachedPesquisador, citacao));
            }
        }
        return this;
    }

    public PesquisadorBuilder endereco(EnderecoLattes endereco) {

        if (cachedPesquisador.getEndereco() != null) {
            pesquisador.setEndereco(EnderecoFactory.createEndereco(cachedPesquisador.getEndereco().getIdEndereco(),
                    endereco.getEnderecoProfissional(), endereco.getEnderecoProfissionalLatitude(),
                    endereco.getEnderecoProfissionalLongitude()));
        } else {
            pesquisador.setEndereco(EnderecoFactory.createEndereco(endereco.getEnderecoProfissional(),
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

    public PesquisadorBuilder formacaoAcademica(FormacoesAcademicaLattes formacoes) {

        for (FormacaoLattes formacaoLattes : formacoes.getFormacoes()) {
             FormacaoAcademica formacao = null;
             if ((formacao = cachedPesquisador.containsFormacaoAcademica(formacaoLattes.getDescricao())) != null) {
                pesquisador.getFormacoes().add(
                        FormacaoAcademicaFactory.createFormacaoAcademica(formacao.getIdFormacaoAcademica(),
                                formacaoLattes.getAnoConclusao(), formacaoLattes.getAnoInicio(),
                                formacaoLattes.getDescricao(), formacaoLattes.getNomeInstituicao(),
                                formacaoLattes.getTipo(), cachedPesquisador));
             }else{
                pesquisador.getFormacoes().add(
                        FormacaoAcademicaFactory.createFormacaoAcademica(
                    formacaoLattes.getAnoConclusao(), formacaoLattes.getAnoInicio(), formacaoLattes.getDescricao(),
                    formacaoLattes.getNomeInstituicao(), formacaoLattes.getTipo(), cachedPesquisador));
             }

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