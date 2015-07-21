package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "projetos_pesquisa")
public class ProjetetosPesquisaLattes {

    @XmlElement(name = "projeto")
    private List<ProjetoLattes> projetos;

    public List<ProjetoLattes> getProjetos() {
        return projetos;
    }
}