package br.ufscar;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProducaoPesquisador extends br.ufscar.Entity {

    private static final long serialVersionUID = -7510333707661579584L;
    // TODO: PEDRO TEST COLUMN ANNOTATION
    @Id
    private Long id;
    private String titulo;
    private Integer ano;
    private String link;

    @ElementCollection
    private List<String> autores;

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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProducaoPesquisador other = (ProducaoPesquisador) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}