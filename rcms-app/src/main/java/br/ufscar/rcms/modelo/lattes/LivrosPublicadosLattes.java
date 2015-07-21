package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "livros_publicados")
public class LivrosPublicadosLattes {

    @XmlElement(name = "livro")
    private List<LivroLattes> livros;

    public List<LivroLattes> getLivros() {
        return livros;
    }
}
