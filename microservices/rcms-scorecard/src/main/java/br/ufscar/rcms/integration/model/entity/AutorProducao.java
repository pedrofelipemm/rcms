package br.ufscar.rcms.integration.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "autor_producao")
public class AutorProducao extends br.ufscar.rcms.model.entity.Entity {

    private static final long serialVersionUID = -7028167574212537609L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long idAutor;

    @ManyToOne
    @JoinColumn(name = "id_producao", foreignKey = @ForeignKey(name = "fk_autor_producao_producao"))
    private Producao producao;

    @ManyToOne
    @JoinColumn(name = "id_citacao_bibliografica", foreignKey = @ForeignKey(name = "fk_autor_producao_citacao_bibliografica"))
    private CitacaoBibliografica citacaoBibliografica;

    @Column(name = "ordem_autoria", nullable = false)
    private Integer ordemAutoria;

    public AutorProducao() {/* Serialization */}
    
    public AutorProducao(final Producao producao, final CitacaoBibliografica citacaoBibliografica, final Integer ordemAutoria) {
        this.producao = producao;
        this.citacaoBibliografica = citacaoBibliografica;
        this.ordemAutoria = ordemAutoria;
    }

    @Override
    public Long getId() {
        return idAutor;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(final Long idAutor) {
        this.idAutor = idAutor;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(final Producao producao) {
        this.producao = producao;
    }

    public CitacaoBibliografica getCitacaoBibliografica() {
        return citacaoBibliografica;
    }

    public void setCitacaoBibliografica(final CitacaoBibliografica citacaoBibliografica) {
        this.citacaoBibliografica = citacaoBibliografica;
    }

    public Integer getOrdemAutoria() {
        return ordemAutoria;
    }

    public void setOrdemAutoria(final Integer ordemAutoria) {
        this.ordemAutoria = ordemAutoria;
    }
}