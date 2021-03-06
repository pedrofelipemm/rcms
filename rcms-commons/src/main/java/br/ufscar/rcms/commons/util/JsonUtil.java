package br.ufscar.rcms.commons.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtil {

    private static final Gson GSON = new GsonBuilder().create();

    private JsonUtil() {}

    public static Gson getInstance() {
        return GSON;
    }

    /**
     * Referência circular poderá lançar {@linkplain StackOverflowError}
     */
    public static String toJson(final Object object) {
        return GSON.toJson(object);
    }
}