package com.radosti.app.config;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = AppConfig.class.getClassLoader().getResourceAsStream("app.properties")) {
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load config", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
