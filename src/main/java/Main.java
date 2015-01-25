import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import br.ufscar.rcms.modelo.lattes.CurriculoLattes;
import br.ufscar.rcms.util.XMLUtils;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);

        new Main().test();

    }

    public void test() {

        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dc-ufscar-database.xml");
            String test = IOUtils.toString(resourceAsStream, "UTF-8");

            try {
                CurriculoLattes curriculoLattes = XMLUtils.xmlToObject(CurriculoLattes.class, test);

                System.out.println(curriculoLattes);
            } catch (JAXBException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
