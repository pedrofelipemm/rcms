package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ARTIGO_ACEITO_PARA_PUBLICACAO")
public class ArtigoAceitoParaPublicacao extends ProducaoBibliografica {

    private static final long serialVersionUID = -1913309084588789965L;
}