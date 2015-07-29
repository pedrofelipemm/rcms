package br.ufscar.rcms.scorecard.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtil {

    private static final Gson GSON = new GsonBuilder().create();

    private JsonUtil() {}

    /**
     * Referência circular poderá lançar {@linkplain StackOverflowError}
     */
    public static String toJson(final Object object) {
        return GSON.toJson(object);
    }
}