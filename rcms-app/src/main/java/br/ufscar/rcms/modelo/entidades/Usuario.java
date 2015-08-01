package br.ufscar.rcms.modelo.entidades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufscar.rcms.modelo.entidades.Configuracao.Tipos;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends Entidade {

    private static final long serialVersionUID = 5066890213009565801L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    protected Long idUsuario;

    @Column(name = "nome", nullable = false)
    protected String nome;

    @Column(name = "flag_administrador", nullable = false)
    protected Boolean flagAdministrador;

    @Column(name = "login", nullable = false, unique = true)
    protected String login;

    @Column(name = "senha", nullable = false)
    protected String senha;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(columnDefinition = "boolean default true")
    protected boolean enabled;

    @OneToMany
    protected List<Autorizacao> autorizacoes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected Set<Configuracao> configuracoes = new HashSet<Configuracao>();

    public Usuario() {
        configuracoes = initConfiguracoes();
    }

    private Set<Configuracao> initConfiguracoes() {
        final Set<Configuracao> result = new HashSet<Configuracao>();
        for (Tipos tipo : Configuracao.Tipos.values()) {
            result.add(new Configuracao(tipo));
        }
        return result;
    }

    public Set<Configuracao> getConfiguracoes() {
        return configuracoes;
    }

    public Configuracao getConfiguracao(final Configuracao.Tipos tipo) {
        return configuracoes.stream().filter(c -> c.getKey().equals(tipo)).findFirst().orElse(null);
    }

    public void addConfiguracao(final Configuracao configuracao) {
        configuracoes.remove(configuracao);
        configuracoes.add(configuracao);
    }

    public void removeConfiguracao(final Configuracao configuracao) {
        configuracoes.remove(configuracao);
    }

    public List<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(final List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Boolean getFlagAdministrador() {
        return flagAdministrador;
    }

    public void setFlagAdministrador(final Boolean flagAdministrador) {
        this.flagAdministrador = flagAdministrador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) obj;
        if (idUsuario == null) {
            if (other.idUsuario != null) {
                return false;
            }
        } else if (!idUsuario.equals(other.idUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + "]";
    }
}