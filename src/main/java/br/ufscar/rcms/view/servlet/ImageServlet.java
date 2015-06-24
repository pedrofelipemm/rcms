package br.ufscar.rcms.view.servlet;

import static br.ufscar.rcms.util.MiscellanyUtil.isEmpty;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.PesquisadorService;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = -1318717025356793573L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServlet.class);

    protected static final String IMAGES_PATH = "/images/";
    protected static final String DEFAULT_IMAGE = IMAGES_PATH + "person.png";

    @Autowired
    private PesquisadorService pesquisadorService;

    private byte[] defaultImage;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        Long userId = extractUserId(request);
        TransientFile file = pesquisadorService.buscarFoto(userId);

        byte[] image = loadImage(file);

        downloadFile(response, image);
    }

    private Long extractUserId(final HttpServletRequest request) {
        return Long.valueOf(request.getPathInfo().substring(1));
    }

    private byte[] loadImage(final TransientFile file) {
        return isEmpty(file) ? getDefaultImage() : file.getFile();
    }

    private byte[] getDefaultImage() {
        return !isEmpty(defaultImage) ? defaultImage : loadDefaultImage();
    }

    private byte[] loadDefaultImage() {
        try {
            String imagePath = this.getClass().getResource(DEFAULT_IMAGE).getPath();
            defaultImage = IOUtils.toByteArray(new FileInputStream(new File(imagePath)));
            return defaultImage;
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
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