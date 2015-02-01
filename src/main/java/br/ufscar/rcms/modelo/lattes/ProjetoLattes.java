package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="projeto")
public class ProjetoLattes extends BaseLattes{

    private static final long serialVersionUID = -211387611691688824L;

    @XmlElement(name = "ano_inicio")
    private Integer anoInicio;

    @XmlElement(name = "ano_conclusao")
    private Integer anoConclusao;

    @XmlElement
    private String nome;

    @XmlElement
    private String descricao;

    public Integer getAnoInicio() {
        return anoInicio;
    }

    public Integer getAnoConclusao() {
        return anoConclusao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}