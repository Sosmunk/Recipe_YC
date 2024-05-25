package com.triangular.recipe_yc.web.request;

public abstract class ApiRequest {
    public static String trim(String s) {
        if (s == null)
            return null;

        return s.trim();
    }

    public static String upper(String s) {
        if (s == null)
            return null;

        return s.toUpperCase();
    }
}
