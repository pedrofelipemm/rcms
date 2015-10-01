package br.ufscar.rcms.view.servlet;

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
import br.ufscar.rcms.servico.ProjetoPesquisaService;

@WebServlet("/galeria/*")
public class GaleriaServlet extends HttpServlet {

    private static final long serialVersionUID = 9079499391021534736L;
    private static final Logger LOGGER = LoggerFactory.getLogger(GaleriaServlet.class);

    protected static final String IMAGES_PATH = "/images/";

    @Autowired
    private ProjetoPesquisaService projetoPesquisaService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {

        String fileName = extractFileName(request);
        TransientFile file = projetoPesquisaService.buscarGaleria(fileName);

        byte[] image = file.getFile();

        downloadFile(response, image);
    }

    private String extractFileName(final HttpServletRequest request) {
        return request.getPathInfo().substring(1);
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
