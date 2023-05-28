package service.managers;

import service.exceptions.PropertyNotFoundException;
import service.interfaces.IPropertiesManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesManager implements IPropertiesManager {

    private static PropertiesManager instance =  null;

    public static PropertiesManager getInstance() {
        if(instance == null){
            instance = new PropertiesManager();
        }
        return instance;
    }

    private PropertiesManager() {}
    @Override
    public Properties loadPropertiesFromResourceName(String path) throws IOException, URISyntaxException {
        return loadProperties(this.getClass().getResource(path).toURI());
    }
    @Override
    public Properties loadProperties(URI uri) throws IOException {
        File file = new File(uri);
        Properties properties = new Properties();
        properties.load(new BufferedReader(new FileReader(file)));
        return properties;
    }

    @Override
    public Properties loadProperties(URL url) throws IOException, URISyntaxException {
        return loadProperties(url.toURI());
    }

    @Override
    public String getProperty(Properties properties, String key) throws PropertyNotFoundException {
        String value = properties.getProperty(key);
        if(value == null) throw new PropertyNotFoundException(key);
        return value;
    }

    @Override
    public void setProperty(Properties properties, String key, String value, String path) throws IOException {
        Files.write(Paths.get(path), (key + " = " + value).getBytes(), StandardOpenOption.APPEND);
        properties.setProperty(key, value);
    }

    @Override
    public Map<String, String> getPropertiesMap(Properties properties) throws IOException {
        Map<String, String> propMap = new HashMap<>();

        Enumeration enProp = properties.propertyNames();
        String key;

        do {
            key = (String) enProp.nextElement();
            propMap.put(key, properties.getProperty(key));
        } while(enProp.hasMoreElements());

        return propMap;
    }
}