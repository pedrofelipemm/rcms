package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participacao_evento")
public class ParticipacaoEventoLattes extends BaseLattes {

    private static final long serialVersionUID = -9131765904322231410L;

    @XmlElement(name = "evento")
    private List<EventoLatttes> eventos;

    public List<EventoLatttes> getEventos() {
        return eventos;
    }
}