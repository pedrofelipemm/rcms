package br.ufscar.rcms.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import br.ufscar.rcms.factory.AtuacaoPesquisadorFactory;
import br.ufscar.rcms.factory.CompreensaoIdiomaFactory;
import br.ufscar.rcms.factory.EnderecoFactory;
import br.ufscar.rcms.factory.FormacaoAcademicaFactory;
import br.ufscar.rcms.factory.OrientacaoFactory;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoUsuario;
import br.ufscar.rcms.modelo.entidades.Doutorado;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.IniciacaoCientifica;
import br.ufscar.rcms.modelo.entidades.Mestrado;
import br.ufscar.rcms.modelo.entidades.OrganizacaoEvento;
import br.ufscar.rcms.modelo.entidades.Orientacao;
import br.ufscar.rcms.modelo.entidades.OrientacaoOutroTipo;
import br.ufscar.rcms.modelo.entidades.ParticipacaoEvento;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.PremioTitulo;
import br.ufscar.rcms.modelo.entidades.TCC;
import br.ufscar.rcms.modelo.lattes.AreaAtuacaoLattes;
import br.ufscar.rcms.modelo.lattes.EnderecoLattes;
import br.ufscar.rcms.modelo.lattes.EventoLatttes;
import br.ufscar.rcms.modelo.lattes.FormacaoLattes;
import br.ufscar.rcms.modelo.lattes.FormacoesAcademicaLattes;
import br.ufscar.rcms.modelo.lattes.IdentificacaoLattes;
import br.ufscar.rcms.modelo.lattes.IdiomaLattes;
import br.ufscar.rcms.modelo.lattes.IdiomasLattes;
import br.ufscar.rcms.modelo.lattes.OrganizacaoEventoLattes;
import br.ufscar.rcms.modelo.lattes.OrientacaoLattes;
import br.ufscar.rcms.modelo.lattes.ParticipacaoEventoLattes;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.modelo.lattes.PremioLattes;
import br.ufscar.rcms.modelo.lattes.PremiosLattes;

public class PesquisadorBuilder implements Builder<Pesquisador> {

    public static final Logger LOGGER = LoggerFactory.getLogger(PesquisadorBuilder.class);

    private Pesquisador pesquisador;
    private Pesquisador cachedPesquisador;

    public PesquisadorBuilder(final String login, final String nome, final String senha, final String codigoLattes, final String email,
            final String resumoProfissional) {
        this(null, login, nome, senha, codigoLattes, email, resumoProfissional);
    }

    public PesquisadorBuilder(final Long idUsuario, final String login, final String nome, final String senha, final String codigoLattes,
            final String email, final String resumoProfissional) {

        pesquisador = new Pesquisador();
        pesquisador.setIdUsuario(idUsuario);
        pesquisador.setLogin(login);
        pesquisador.setNome(nome);
        pesquisador.setSenha(senha);
        pesquisador.setCodigoLattes(codigoLattes);
        pesquisador.setEmail(email);
        pesquisador.setResumoProfissional(resumoProfissional);

        validatePesquisador(pesquisador);
    }

    public PesquisadorBuilder(final PesquisadorLattes pesquisadorLattes, final Pesquisador pesquisador) {

        this(pesquisador.getIdUsuario(), pesquisador.getLogin(), pesquisadorLattes.getIdentificacao().getNomeCompleto(),
                pesquisador.getSenha(), pesquisador.getCodigoLattes(), pesquisador.getEmail(), pesquisador.getResumoProfissional());

        this.pesquisador.setSexo(pesquisadorLattes.getIdentificacao().getSexo());
        validatePesquisador(pesquisadorLattes, pesquisador);
        cachedPesquisador = pesquisador;
    }

    /*
     * public PesquisadorBuilder projetosPesquisa(final ProjetosPesquisaLattes projetosPesquisa) { if
     * (projetosPesquisa!=null) { for (ProjetoLattes projetoLattes : projetosPesquisa.getProjetos()) {
     * pesquisador.addProjetosPesquisa(new ProjetoPesquisa(projetoLattes.getNome(), projetoLattes .getDescricao(),
     * projetoLattes.getAnoInicio(), projetoLattes.getAnoConclusao())); } } return this; }
     */
    public PesquisadorBuilder areaAtuacoes(final AreaAtuacaoLattes areaAtuacao) {
        if (areaAtuacao != null) {
            for (String descricao : areaAtuacao.getDescricao()) {
                pesquisador.addAtuacoesPesquisador(AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricao));
            }
        }
        return this;
    }

    public PesquisadorBuilder orientacoes(final PesquisadorLattes pesquisadorLattes) {

        pesquisador.addOrientacoes(buildOrientacao(pesquisadorLattes.getOrientacaoDoutoradoAndamento().getTeses(), Doutorado.class));
        pesquisador.addOrientacoes(buildOrientacao(pesquisadorLattes.getOrientacaoDoutoradoConcluido().getTeses(), Doutorado.class));
        pesquisador.addOrientacoes(buildOrientacao(pesquisadorLattes.getOrientacaoIniciacaoCientificaConcluido().getIniciacaoCientifica(), IniciacaoCientifica.class));
        pesquisador.addOrientacoes(buildOrientacao(pesquisadorLattes.getOrientacaoMestradoAndamento().getDissertacoes(), Mestrado.class));
        pesquisador.addOrientacoes(buildOrientacao(pesquisadorLattes.getOrientacaoMestradoConcluido().getDissertacoes(), Mestrado.class));
        pesquisador.addOrientacoes(buildOrientacao(pesquisadorLattes.getOrientacaoOutrosTipoConcluido().getOrientacaoOutra(), OrientacaoOutroTipo.class));
        pesquisador.addOrientacoes(buildOrientacao(pesquisadorLattes.getOrientacaoTCCConcluido().getTccs(), TCC.class));

        return this;
    }

    public PesquisadorBuilder organizacaoEventos(final OrganizacaoEventoLattes organizacaoEvento) {
        if (organizacaoEvento != null) {
            for (EventoLatttes eventoLatttes : organizacaoEvento.getEventos()) {
                pesquisador.addOrgazicaoEventos(new OrganizacaoEvento(cachedPesquisador, eventoLatttes.getTitulo(),
                        eventoLatttes.getNatureza(), eventoLatttes.getAno()));
            }
        }
        return this;
    }

    public PesquisadorBuilder participacaoEventos(final ParticipacaoEventoLattes participacaoEvento) {
        if (participacaoEvento != null) {
            for (EventoLatttes eventoLatttes : participacaoEvento.getEventos()) {
                pesquisador.addParticipacaoEventos(new ParticipacaoEvento(cachedPesquisador, eventoLatttes.getTitulo(), eventoLatttes.getAno()));
            }
        }
        return this;
    }

    public PesquisadorBuilder premios(final PremiosLattes premios) {
        if (premios != null) {
            for (PremioLattes premioLattes : premios.getPremiosTitulos()) {
                pesquisador.addPremios(new PremioTitulo(cachedPesquisador, premioLattes.getAno(), premioLattes.getDescricao()));
            }
        }
        return this;
    }

    public PesquisadorBuilder compreensaoIdiomas(final IdiomasLattes idiomas) {
        if (idiomas != null) {
            for (IdiomaLattes idiomaLattes : idiomas.getIdiomas()) {
                pesquisador.addCompreensaoIdiomas(CompreensaoIdiomaFactory.createCompreensaoIdioma(new Idioma(
                        idiomaLattes.getNome()), idiomaLattes.getProficiencia(), cachedPesquisador));
            }
        }
        return this;
    }

    public PesquisadorBuilder citacaoBibliografica(final IdentificacaoLattes identificacao) {

        if (identificacao != null) {
            String[] citacoes = identificacao.getNomeCitacaoBibliografica().split(";");
            for (String citacao : citacoes) {
                pesquisador.addCitacaoBibliografica(new CitacaoBibliografica(cachedPesquisador, citacao.trim()));
            }
        }
        return this;
    }

    public PesquisadorBuilder endereco(final EnderecoLattes endereco) {
        if (endereco != null) {
            if (cachedPesquisador.getEndereco() != null) {
                pesquisador.setEndereco(EnderecoFactory.createEndereco(cachedPesquisador.getEndereco().getIdEndereco(),
                        endereco.getEnderecoProfissional(), endereco.getEnderecoProfissionalLatitude(),
                        endereco.getEnderecoProfissionalLongitude()));
            } else {
                pesquisador.setEndereco(EnderecoFactory.createEndereco(endereco.getEnderecoProfissional(),
                        endereco.getEnderecoProfissionalLatitude(), endereco.getEnderecoProfissionalLongitude()));
            }
        }
        return this;
    }

    public PesquisadorBuilder endereco(final Endereco endereco) {

        pesquisador.setEndereco(endereco);
        return this;
    }

    public PesquisadorBuilder formacaoAcademica(final FormacaoAcademica... formacao) {

        pesquisador.setFormacoes(Arrays.asList(formacao));
        return this;
    }

    public PesquisadorBuilder formacaoAcademica(final List<FormacaoAcademica> formacoes) {

        pesquisador.setFormacoes(formacoes);
        return this;
    }

    public PesquisadorBuilder formacaoAcademica(final FormacoesAcademicaLattes formacoes) {
        if (formacoes != null) {
            for (FormacaoLattes formacaoLattes : formacoes.getFormacoes()) {
                FormacaoAcademica formacao = null;
                if ((formacao = cachedPesquisador.containsFormacaoAcademica(formacaoLattes)) != null) {
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
        }
        return this;
    }

    public PesquisadorBuilder configuracao(final Set<ConfiguracaoUsuario> configuracoes) {
        configuracoes.forEach(pesquisador::addConfiguracao);
        return this;
    }

    @Override
    public Pesquisador build() {
        return pesquisador;
    }

    private void validatePesquisador(final Pesquisador pesquisador) {
        Validate.notBlank(pesquisador.getLogin());
        Validate.notBlank(pesquisador.getNome());
        Validate.notBlank(pesquisador.getSenha());
        Validate.notBlank(pesquisador.getCodigoLattes());
        Validate.notBlank(pesquisador.getEmail());
        // Validate.notBlank(pesquisador.getResumoProfissional());
    }

    private void validatePesquisador(final PesquisadorLattes pesquisadorLattes, final Pesquisador pesquisador) {

        validatePesquisador(pesquisador);

        if (!pesquisador.getCodigoLattes().equals(pesquisadorLattes.getCodigoLattes())) {
            throw new IllegalStateException("Código lattes dos pequisadores não são iguais!");
        }
    }

    private List<? extends Orientacao> buildOrientacao(final List<? extends OrientacaoLattes> orientacoes, final Class<? extends Orientacao> clazz) {

        if (CollectionUtils.isEmpty(orientacoes)) {
            return null;
        }

        List<Orientacao> orientacaoList = new ArrayList<Orientacao>();
        for (OrientacaoLattes orientacao : orientacoes) {
            try {
                orientacaoList.add(OrientacaoFactory.createOrientacao(cachedPesquisador, orientacao.getNomeAluno(),
                        orientacao.getInstituicao(), orientacao.getAgenciaFomento(), orientacao.getTipoOrientacao(),
                        orientacao.getTituloTrabalho(), clazz));
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return orientacaoList;
    }
}