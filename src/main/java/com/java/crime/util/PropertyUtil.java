package com.java.crime.util;

import java.util.ResourceBundle;

public class PropertyUtil {
    public static String getPropertyString() {
        ResourceBundle rb = ResourceBundle.getBundle("db");

        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        return url + "?user=" + user + "&password=" + password;
    }
}
