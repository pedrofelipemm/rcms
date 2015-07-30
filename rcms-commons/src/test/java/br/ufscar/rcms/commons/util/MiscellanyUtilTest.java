package br.ufscar.rcms.commons.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.ufscar.rcms.commons.util.MiscellanyUtil;

public class MiscellanyUtilTest {

    private static final String NOT_EMPTY = "notEmpty";

    @Test
    public void instanciationTest() throws Exception {

        final Class<?> clazz = MiscellanyUtil.class;
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());
    }

    @Test
    public void isEmptyTest() {

        Map<String, String> nonEmptyMap = new HashMap<String, String>();
        nonEmptyMap.put(NOT_EMPTY, NOT_EMPTY);

        Object[] nonNullObjects = new Object[] { new Object(), NOT_EMPTY, BigDecimal.valueOf(10), Integer.valueOf(10),
                Double.valueOf(10), new Date(), new Object[] { NOT_EMPTY }, Arrays.asList(NOT_EMPTY), nonEmptyMap,
                new byte[2048] };

        Object[] nullObjects = new Object[] { null, "", new Object[] {}, new ArrayList<String>(),
                new HashMap<String, String>(), new byte[0] };

        for (Object param : nonNullObjects) {
            assertFalse(MiscellanyUtil.isEmpty(param));
        }

        for (Object param : nullObjects) {
            assertTrue(MiscellanyUtil.isEmpty(param));
        }

    }
}