package com.epam.training.tasks.stoss.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public Properties load(String file) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(file)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            return properties;
        }
    }
}
