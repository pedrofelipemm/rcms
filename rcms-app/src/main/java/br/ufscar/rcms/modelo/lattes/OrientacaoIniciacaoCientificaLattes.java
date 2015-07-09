package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orientacao_iniciacao_cientifica_concluido")
public class OrientacaoIniciacaoCientificaLattes extends BaseLattes {

    private static final long serialVersionUID = -3946750591777006579L;

    @XmlElement(name = "iniciacao_cientifica")
    private List<IniciacaoCientificaLattes> iniciacaoCientifica;

    public List<IniciacaoCientificaLattes> getIniciacaoCientifica() {
        return iniciacaoCientifica;
    }
}