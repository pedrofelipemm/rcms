package br.ufscar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    private static final Gson GSON = new GsonBuilder().create();
    
    private JsonUtil() {}

    public static String toJson(final Object object) {
        return GSON.toJson(object);
    }

    public static void main(String[] args) {
        Idioma i = new Idioma("teste");
        i.setId(2L);
        System.out.println(toJson(i));
    }

}