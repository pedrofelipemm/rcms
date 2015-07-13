import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufscar.rcms.modelo.lattes.BaseLattes;

@XmlRootElement(name = "CURRICULO-VITAE")
public class CurriculoLattes extends BaseLattes {

    private static final long serialVersionUID = 1295841626217025133L;

    @XmlAttribute(name = "SISTEMA-ORIGEM-XML")
    private String sistemaOrigemXml;

    @XmlAttribute(name = "DATA-ATUALIZACAO")
    private String dataAtualizacao;

    @XmlAttribute(name = "HORA-ATUALIZACAO")
    private String horaAtualizacao;

    @XmlAttribute(name = "NUMERO-IDENTIFICADOR")
    private String numeroIdentificador;
}