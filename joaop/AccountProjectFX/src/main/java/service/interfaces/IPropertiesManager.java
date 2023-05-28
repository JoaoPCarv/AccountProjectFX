package service.interfaces;

import service.exceptions.PropertyNotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public interface IPropertiesManager {

     Properties loadPropertiesFromResourceName(String path) throws IOException, URISyntaxException;
     Properties loadProperties(URI uri) throws IOException;
     Properties loadProperties(URL url) throws IOException, URISyntaxException;
     String getProperty(Properties properties, String key) throws IOException, PropertyNotFoundException;
     void setProperty(Properties properties, String key, String value, String path) throws IOException;
     Map<String, String> getPropertiesMap(Properties properties) throws IOException;


}
