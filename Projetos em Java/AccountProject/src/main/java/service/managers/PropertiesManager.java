package service.managers;

import service.exceptions.PropertyNotFoundException;
import service.interfaces.PropertiesManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractPropertiesManager implements PropertiesManager {

    @Override
    public Properties loadProperties(String path) throws IOException {
        File file = new File(path);
        Properties properties = new Properties();
        properties.load(new BufferedReader(new FileReader(file)));
        return properties;
    }

    @Override
    public String getProperty(Properties properties, String key) throws PropertyNotFoundException {
        String value = properties.getProperty(key);
        if(value == null) throw new PropertyNotFoundException(key);
        return value;
    }

    @Override
    public void setProperty(Properties properties, String key, String value) {
        properties.setProperty(key, value);
    }

}
