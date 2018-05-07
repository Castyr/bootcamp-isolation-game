package org.bootcamp.isolationgame.core.utils;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Props {
    private static Props instance = null;
    private ResourceBundle rb;

    protected Props() {
        try {
            rb = PropertyResourceBundle.getBundle("config");
        } catch (Exception e) {
            System.out.println("Failed to find config!");
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        if (rb.containsKey(key)) {
            return rb.getString(key);
        }
        System.out.println(String.format("No such key '%s' exists in configuration.", key));
        return null;
    }

    public static Props getInstance() {
        if (instance == null) {
            instance = new Props();
        }
        return instance;
    }
}
