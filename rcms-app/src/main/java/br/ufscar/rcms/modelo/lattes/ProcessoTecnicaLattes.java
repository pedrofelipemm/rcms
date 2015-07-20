package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "processo_tecnica")
public class ProcessoTecnicaLattes {

    @XmlElement(name = "produto")
    private List<ProdutoProcessoTecnicaLattes> produtos;

    public List<ProdutoProcessoTecnicaLattes> getProdutos() {
        return produtos;
    }
}
