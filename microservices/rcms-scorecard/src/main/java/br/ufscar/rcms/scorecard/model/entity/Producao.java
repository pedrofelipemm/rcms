package br.ufscar.rcms.scorecard.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Entity;
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
    private String title;
    private List<String> authors;
    private Integer year;
    private Date lastUpdate;

    public Producao() {/* Serialization */}

    public Producao(final String title, final List<String> authors, final Integer year, final Date lastUpdate) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.lastUpdate = lastUpdate;
    }

    public Producao(final br.ufscar.rcms.integration.model.entity.Producao producaoIntegration) {
        this(producaoIntegration.getTitulo(), producaoIntegration.getAutores().stream()
                .map(e -> e.getCitacaoBibliografica()).filter(Objects::nonNull).map(e -> e.getNomeCitacao())
                .collect(Collectors.toList()), producaoIntegration.getAno(), new Date());
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
        return lastUpdate;
    }
}