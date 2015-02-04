package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "colaboradores")
public class ColaboradoresLattes extends BaseLattes {

    private static final long serialVersionUID = -6693977138385784210L;

    @XmlElement(name = "id_lattes_colaborador")
    private List<String> idColaborador;

    public List<String> getIdColaborador() {
        return idColaborador;
    }
}