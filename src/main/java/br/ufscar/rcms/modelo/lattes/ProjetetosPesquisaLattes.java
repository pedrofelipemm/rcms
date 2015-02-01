package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "projetos_pesquisa")
public class ProjetetosPesquisaLattes extends BaseLattes{

    private static final long serialVersionUID = -6889888043193927515L;

    @XmlElement(name = "projeto")
    private List<ProjetoLattes> projetos;

    public List<ProjetoLattes> getProjetos() {
        return projetos;
    }
}