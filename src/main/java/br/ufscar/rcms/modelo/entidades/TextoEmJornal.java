package br.ufscar.rcms.modelo.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"TEXTO_EM_JORNAL\"")
public class TextoEmJornal extends ProducaoBibliografica {

    private static final long serialVersionUID = 5261866874043980414L;

    @Column(name = "nome_jornal")
    private String nomeJornal;

    @Column(name = "data_publicacao")
    private Date dataPublicacao;

    public String getNomeJornal() {
        return nomeJornal;
    }

    public void setNomeJornal(String nomeJornal) {
        this.nomeJornal = nomeJornal;
    }

    public Date getDataPublicacao() {
        return new Date(dataPublicacao.getTime());
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = new Date(dataPublicacao.getTime());
    }
}