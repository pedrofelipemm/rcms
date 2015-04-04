package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"ARTIGO_ACEITO_PARA_PUBLICACAO\"")
@ForeignKey(name = "fk_artigo_aceito_para_publicacao_producao_bibliografica")
public class ArtigoAceitoParaPublicacao extends ProducaoBibliografica {

    private static final long serialVersionUID = -1913309084588789965L;
}