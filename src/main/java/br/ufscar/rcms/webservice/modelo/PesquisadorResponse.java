package br.ufscar.rcms.webservice.modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pesquisador")
public class PesquisadorResponse extends Response {

    private static final long serialVersionUID = 1823269994306988192L;

    @XmlElement
    private Long idUsuario;

    @XmlElement
    private String login;

    @XmlElement
    private String nome;

    @XmlElement
    private String senha;

    @XmlElement
    private String codigoLattes;

    @XmlElement
    private String email;

    @XmlElement(name = "admin")
    private boolean flagAdministrador;

    @XmlElement
    private String resumoProfissional;

    @XmlElement
    private EnderecoResponse endereco;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public void setCodigoLattes(String codigoLattes) {
        this.codigoLattes = codigoLattes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFlagAdministrador() {
        return flagAdministrador;
    }

    public void setFlagAdministrador(boolean flagAdministrador) {
        this.flagAdministrador = flagAdministrador;
    }

    public String getResumoProfissional() {
        return resumoProfissional;
    }

    public void setResumoProfissional(String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponse endereco) {
        this.endereco = endereco;
    }
}