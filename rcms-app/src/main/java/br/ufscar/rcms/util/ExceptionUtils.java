package br.ufscar.rcms.util;

public abstract class ExceptionUtils {

    public static Throwable getInnerCause(Throwable throwable) {

        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        return throwable;
    }
}