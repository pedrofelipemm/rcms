package br.ufscar.rcms.util;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

public abstract class ExceptionUtils {

    public static Throwable getInnerCause(final Throwable throwable) {

        Throwable t = throwable;
        while (isEmpty(t.getCause())) {
            t = throwable.getCause();
        }

        return t;
    }
}