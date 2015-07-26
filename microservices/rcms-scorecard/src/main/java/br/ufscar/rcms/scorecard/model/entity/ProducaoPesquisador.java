package br.ufscar.rcms.scorecard.model.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import br.ufscar.rcms.scorecard.util.JsonUtil;

@Entity
public class ProducaoPesquisador extends br.ufscar.rcms.scorecard.model.entity.Entity {

    private static final long serialVersionUID = -7510333707661579584L;
    // TODO: PEDRO TEST COLUMN ANNOTATION
    @Id
    private Long id;
    private String titulo;
    private Integer ano;
    private String link;

    @ElementCollection
    private List<String> autores;

    public ProducaoPesquisador() {/* Serialization */}

    public ProducaoPesquisador(final String titulo, final Integer ano, final String link, final List<String> autores) {
        this.titulo = titulo;
        this.ano = ano;
        this.link = link;
        this.autores = autores;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getLink() {
        return link;
    }

    public List<String> getAutores() {
        return autores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProducaoPesquisador other = (ProducaoPesquisador) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}