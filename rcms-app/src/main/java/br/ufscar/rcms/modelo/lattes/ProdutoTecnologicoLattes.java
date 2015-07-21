package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "processo_tecnica")
public class ProdutoTecnologicoLattes {

    @XmlElement(name = "produto")
    private List<ProdutoLattes> produtos;

    public List<ProdutoLattes> getProdutos() {
        return produtos;
    }
}
