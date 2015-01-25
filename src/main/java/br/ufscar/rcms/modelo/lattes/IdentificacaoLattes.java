package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "identificacao")
public class IdentificacaoLattes extends BaseLattes {

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
}