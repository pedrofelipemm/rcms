package br.ufscar.rcms.view.servlet;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.ProducaoService;

@WebServlet("/files/*")
public class FileServlet extends HttpServlet {

    private static final long serialVersionUID = -1318717025356793573L;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServlet.class);

    @Autowired
    private ProducaoService producaoService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        Long producaoId = extractProducaoId(request);
        TransientFile file = producaoService.buscarPdf(producaoId);

        byte[] image = loadPdf(file);

        downloadFile(response, image);
    }

    private Long extractProducaoId(final HttpServletRequest request) {
        String id = request.getPathInfo().substring(1);
        return !isEmpty(id) ? Long.valueOf(id) : Long.valueOf(-1);
    }

    private byte[] loadPdf(final TransientFile file) {
        return TransientFile.isEmpty(file) ? null : file.getFile();
    }

    private void downloadFile(final HttpServletResponse response, final byte[] foto) throws IOException {
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(new ByteArrayInputStream(foto));
            output = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[8192];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ioException) {
                    LOGGER.error(ioException.getMessage(), ioException);
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ioException) {
                    LOGGER.error(ioException.getMessage(), ioException);
                }
            }
        }
    }
}
