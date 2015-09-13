package br.ufscar.rcms.integration.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import br.ufscar.rcms.commons.util.JsonUtil;

@Entity
@Table(name = "pesquisador")
@SuppressWarnings("deprecation")
@ForeignKey(name = "fk_pesquisador_usuario")
public class Pesquisador extends Usuario {

    private static final long serialVersionUID = 7468024654193724256L;

    @Column(name = "codigo_lattes", nullable = false, unique = true)
    private String codigoLattes;

    @Column(name = "resumo_profissional", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String resumoProfissional;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<CitacaoBibliografica> citacaoBibliograficas = new ArrayList<CitacaoBibliografica>();

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public void setCodigoLattes(final String codigoLattes) {
        this.codigoLattes = codigoLattes;
    }

    public String getResumoProfissional() {
        return resumoProfissional;
    }

    public void setResumoProfissional(final String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }

    public List<CitacaoBibliografica> getCitacaoBibliograficas() {
        return citacaoBibliograficas;
    }

    public void setCitacaoBibliograficas(final List<CitacaoBibliografica> citacaoBibliograficas) {
        this.citacaoBibliograficas = citacaoBibliograficas;
    }

    public void addCitacaoBibliografica(final CitacaoBibliografica... citacaoBibliografica) {
        if (citacaoBibliografica != null) {
            citacaoBibliograficas.addAll(Arrays.asList(citacaoBibliografica));
        }
    }

    public void removeCitacaoBibliografica(final CitacaoBibliografica... citacaoBibliografica) {
        if (citacaoBibliografica != null) {
            citacaoBibliograficas.removeAll(Arrays.asList(citacaoBibliografica));
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((codigoLattes == null) ? 0 : codigoLattes.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pesquisador other = (Pesquisador) obj;
        if (codigoLattes == null) {
            if (other.codigoLattes != null) {
                return false;
            }
        } else if (!codigoLattes.equals(other.codigoLattes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}