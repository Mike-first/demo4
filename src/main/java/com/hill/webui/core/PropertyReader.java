package com.hill.webui.core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

    protected static Properties properties = null;
    private static final Path PROPERTY_FILE = Paths.get("src/main/resources/webtest.properties").toAbsolutePath();

    public static String get(String key) {
        return getProperty(key, PROPERTY_FILE);
    }

    private static String getProperty(String key, Path propertyFile) {
        if (properties == null) properties = readPropertyFileOnce(propertyFile);
        return properties.getProperty(key);
    }

    private static java.util.Properties readPropertyFileOnce(Path propertyFile) {
        java.util.Properties properties = new java.util.Properties();
        try (InputStreamReader is = new InputStreamReader(
                Files.newInputStream(propertyFile), StandardCharsets.UTF_8
        )) {
            properties.load(is);
        } catch (IOException ignored) {
        }
        return properties;
    }
}
