package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "identificacao")
public class IdentificacaoLattes extends PesquisadorLattes {

    private static final long serialVersionUID = -7677027746469870708L;

    @XmlElement(name = "identificador10")
    private String identificador;

    @XmlElement(name = "nome_inicial")
    private String nomeInicial;

    @XmlElement(name = "nome_completo")
    private String nomeCompleto;

    @XmlElement(name = "nome_citacao_bibliografica")
    private String nomeCitacaoBibliografica;

    @XmlElement
    private String sexo;

    public String getIdentificador() {
        return identificador;
    }

    public String getNomeInicial() {
        return nomeInicial;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeCitacaoBibliografica() {
        return nomeCitacaoBibliografica;
    }

    public String getSexo() {
        return sexo;
    }
}