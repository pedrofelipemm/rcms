package br.ufscar.rcms.modelo.entidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"TEXTO_EM_JORNAL\"")
@ForeignKey(name = "fk_texto_em_jornal_producao_bibliografica")
public class TextoEmJornal extends ProducaoBibliografica {

    private static final long serialVersionUID = 5261866874043980414L;

    @Column(name = "nome_jornal")
    private String nomeJornal;

    @Column(name = "data_publicacao")
    private Date dataPublicacao;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "paginas")
    private String paginas;

    public TextoEmJornal() {
    }

    public TextoEmJornal(String titulo, List<CitacaoBibliografica> autores, Integer ano, String nomeJornal,
            String dataPublicacao, Integer volume, String paginas) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.nomeJornal = nomeJornal;

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dataPublicacao = (java.util.Date) formatter.parse(dataPublicacao);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.volume = volume;
        this.paginas = paginas;
    }

    public String getNomeJornal() {
        return nomeJornal;
    }

    public void setNomeJornal(String nomeJornal) {
        this.nomeJornal = nomeJornal;
    }

    public Date getDataPublicacao() {
        return new Date(dataPublicacao.getTime());
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = new Date(dataPublicacao.getTime());
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }
}