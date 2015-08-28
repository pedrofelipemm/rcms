package br.ufscar.rcms.scorecard.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Producao extends br.ufscar.rcms.model.entity.Entity {

    private static final long serialVersionUID = -1235158716553189331L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authors;

    private String title;
    private Integer year;
    private Date lastUpdate;

    public Producao() {/* Serialization */}

    public Producao(final String title, final List<String> authors, final Integer year, final Date lastUpdate) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.lastUpdate = new Date(lastUpdate.getTime());
    }

    public Producao(final br.ufscar.rcms.integration.model.entity.Producao producaoIntegration) {
        this(producaoIntegration.getTitulo(), producaoIntegration.getAutores().stream().filter(Objects::nonNull)
                .map(e -> e.getCitacaoBibliografica()).filter(Objects::nonNull).map(e -> e.getPesquisador())
                .filter(Objects::nonNull).map(p -> p.getNome()).collect(Collectors.toList()),
                producaoIntegration.getAno(), new Date());
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Integer getYear() {
        return year;
    }

    public Date getLastUpdate() {
        return new Date(lastUpdate.getTime());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
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
        Producao other = (Producao) obj;
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (year == null) {
            if (other.year != null) {
                return false;
            }
        } else if (!year.equals(other.year)) {
            return false;
        }
        return true;
    }
}