package service.interfaces;

import service.exceptions.PropertyNotFoundException;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public interface PropertyManager {

     Properties loadProperties(String path) throws IOException;
     Map<String, String> getPropertiesMap() throws IOException;
     String getProperty(Properties properties, String key) throws IOException, PropertyNotFoundException;
     void setProperty(Properties properties, String key, String value) throws IOException;


}
