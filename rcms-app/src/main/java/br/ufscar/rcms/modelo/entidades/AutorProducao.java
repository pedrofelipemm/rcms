package br.ufscar.rcms.modelo.entidades;

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
public class AutorProducao extends Entidade implements Comparable {

    private static final long serialVersionUID = -7028167574212537609L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private int idAutor;

    @ManyToOne
    @JoinColumn(name = "id_producao", foreignKey = @ForeignKey(name = "fk_autor_producao_producao"))
    private Producao producao;

    @ManyToOne
    @JoinColumn(name = "id_citacao_bibliografica", foreignKey = @ForeignKey(name = "fk_autor_producao_citacao_bibliografica"))
    private CitacaoBibliografica citacaoBibliografica;

    @Column(name = "ordem_autoria", nullable = false)
    private Integer ordemAutoria;

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

    public CitacaoBibliografica getCitacaoBibliografica() {
        return citacaoBibliografica;
    }

    public void setCitacaoBibliografica(CitacaoBibliografica citacaoBibliografica) {
        this.citacaoBibliografica = citacaoBibliografica;
    }

    public Integer getOrdemAutoria() {
        return ordemAutoria;
    }

    public void setOrdemAutoria(Integer ordemAutoria) {
        this.ordemAutoria = ordemAutoria;
    }

    public AutorProducao() {
    }

    public AutorProducao(Producao producao, CitacaoBibliografica citacaoBibliografica, Integer ordemAutoria) {
        this.producao = producao;
        this.citacaoBibliografica = citacaoBibliografica;
        this.ordemAutoria = ordemAutoria;
    }

    @Override
    public int compareTo(Object o) {
        AutorProducao ap = (AutorProducao) o;
        return this.ordemAutoria.compareTo(ap.ordemAutoria);
    }
}
