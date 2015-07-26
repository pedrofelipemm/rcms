package br.ufscar.rcms.modelo.entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "texto_em_jornal")
@ForeignKey(name = "fk_texto_em_jornal_producao_bibliografica")
public class TextoEmJornal extends ProducaoBibliografica {

    private static final long serialVersionUID = 5261866874043980414L;

    @Column(name = "nome_jornal")
    private String nomeJornal;

    @Column(name = "data_publicacao")
    private Date dataPublicacao;

    @Column(name = "volume")
    private String volume;

    @Column(name = "paginas")
    private String paginas;

    public TextoEmJornal() {
    }

    public TextoEmJornal(final String titulo, final List<CitacaoBibliografica> autores, final Integer ano, final String nomeJornal,
 final String dataPublicacao, final String volume, final String paginas) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.nomeJornal = nomeJornal;

        Date date = null;
        if (dataPublicacao.length() > 0) {
            String[] data = dataPublicacao.split(" ");

            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            String dateInString = data[0] + "-" + this.getMes(data[1].replace(".", "")) + "-" + data[2];
            try {
                date = sdf.parse(dateInString);
            } catch (ParseException e) {
                date = null;
            }
        }

        this.dataPublicacao = date;

        this.volume = volume;
        this.paginas = paginas;
    }

    private int getMes(String mes) {

        switch (mes) {
        case "jan":
            return 1;
        case "fev":
            return 2;
        case "mar":
            return 3;
        case "abr":
            return 4;
        case "mai":
            return 5;
        case "jun":
            return 6;
        case "jul":
            return 7;
        case "ago":
            return 8;
        case "set":
            return 9;
        case "out":
            return 10;
        case "nov":
            return 11;
        case "dez":
            return 12;
        default:
            return 0;
        }
    }

    public String getNomeJornal() {
        return nomeJornal;
    }

    public void setNomeJornal(final String nomeJornal) {
        this.nomeJornal = nomeJornal;
    }

    public Date getDataPublicacao() {
        return this.dataPublicacao;
    }

    public void setDataPublicacao(final Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(final String volume) {
        this.volume = volume;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(final String paginas) {
        this.paginas = paginas;
    }
}