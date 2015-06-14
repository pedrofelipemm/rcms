package br.ufscar.rcms.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import br.ufscar.rcms.modelo.entidades.TransientFile;

public abstract class MiscellanyUtil {

    public static boolean isEmpty(final Object param) {

        if (param == null) {
            return true;
        } else if (param instanceof String) {
            return ((String) param).isEmpty();
        } else if (param instanceof BigDecimal) {
            return false;
        } else if (param instanceof Integer) {
            return false;
        } else if (param instanceof Double) {
            return false;
        } else if (param instanceof Date) {
            return false;
        } else if (param instanceof Object[]) {
            return ((Object[]) param).length < 1;
        } else if (param instanceof Collection<?>) {
            return ((Collection<?>) param).isEmpty();
        } else if (param instanceof Map<?, ?>) {
            return ((Map<?, ?>) param).isEmpty();
        } else if (param instanceof TransientFile) {
            return ((TransientFile) param).getFile().length == 0;
        }

        return false;
    }
}