package br.ufscar.rcms.commons.util;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;

public abstract class XMLUtils {

    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(final Class<T> clazz, final String clobContent) throws JAXBException {

        if (isEmpty(clobContent)) {
            return null;
        }

        final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final StringBuffer buffer = new StringBuffer(clobContent);

        return (T) unmarshaller.unmarshal(new StreamSource(new StringReader(buffer.toString())));
    }

    public static String objectToXML(final Object object) throws JAXBException {

        String result = StringUtils.EMPTY;
        if (isEmpty(object)) {
            return result;
        }

        final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        if (!isEmpty(jaxbContext)) {

            final StringWriter writer = new StringWriter();
            final Marshaller marshaller = jaxbContext.createMarshaller();

            if (!isEmpty(marshaller)) {
                marshaller.marshal(object, writer);
                result = writer.toString();
            }
        }

        return result;
    }

    public static String stripSpecialChars(final String curriculoAsString) {
        return curriculoAsString.replaceAll("&", "&amp;");
    }
}