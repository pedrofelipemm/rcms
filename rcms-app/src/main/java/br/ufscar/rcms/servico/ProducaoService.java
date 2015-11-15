package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.StreamedContent;

import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.TransientFile;

public interface ProducaoService extends Serializable {

    void saveOrUpdate(Producao producao);

    void remove(Producao producao);

    List<Producao> buscarTodas();

    List<Producao> buscarTodasComPdf();

    Producao buscarPorId(Long id);

    Boolean exists(String titulo, Integer ano);

    <T> List<T> buscarProducoes(Class<T> clazz);

    <T> List<T> buscarProducoes(Class<T> clazz, final Long idUsuario);

    TransientFile buscarPdf(Producao producao);

    TransientFile buscarPdf(Long producaoId);

    StreamedContent loadPDF(Producao p);

    Producao buscarTodosDados(final long producaoId);
}