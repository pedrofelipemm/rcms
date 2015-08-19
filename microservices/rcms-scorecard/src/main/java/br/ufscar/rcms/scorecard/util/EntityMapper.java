package br.ufscar.rcms.scorecard.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

public final class EntityMapper {

    private EntityMapper() {}

    private static <T> T map(final Class<T> type, final Object... tuple) {

        List<Class<?>> tupleTypes = new ArrayList<Class<?>>();
        for (int i = 0; i < tuple.length; i++) {
            Object field = tuple[i];
            tupleTypes.add(field == null ? type.getConstructors()[0].getParameterTypes()[i] : field.getClass());
        }
        try {
            Constructor<T> constructor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return constructor.newInstance(tuple);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> List<T> mapList(final Class<T> type, final List<Object[]> records) {
        List<T> result = new LinkedList<T>();
        for (Object[] record : records) {
            result.add(map(type, record));
        }
        return result;
    }

    public static <T> List<T> getResultList(final Query query, final Class<T> type) {
        @SuppressWarnings("unchecked")
        List<Object[]> records = query.getResultList();
        return mapList(type, records);
    }
}