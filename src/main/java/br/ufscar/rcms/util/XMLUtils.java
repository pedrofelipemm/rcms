package br.ufscar.rcms.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class XMLUtils {

    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(final Class<T> clazz, final String clobContent) throws JAXBException {

        if (clobContent == null || clobContent.isEmpty()) {
            return null;
        }

        final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final StringBuffer buffer = new StringBuffer(clobContent);

        return (T) unmarshaller.unmarshal(new StreamSource(new StringReader(buffer.toString())));
    }

    public static String objectToXML(final Object object) throws PropertyException, JAXBException {

        String result = "";
        if (object == null) {
            return result;
        }

        final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        if (jaxbContext != null) {

            final StringWriter writer = new StringWriter();
            final Marshaller marshaller = jaxbContext.createMarshaller();

            if (marshaller != null) {
                marshaller.marshal(object, writer);
                return writer.toString();
            }
        }

        return result;
    }

}
