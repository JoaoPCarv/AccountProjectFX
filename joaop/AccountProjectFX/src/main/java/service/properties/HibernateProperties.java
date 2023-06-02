package service.properties;

import service.managers.PropertiesManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

public final class HibernateProperties {

    @Deprecated(since = "1.0")
    private HibernateProperties() { /* This class should not be instantiated. */ }

    public static Properties getHibernateProperties() {
        try{
            return PropertiesManager.getInstance()
                    .loadPropertiesFromResourceName("/persistence.properties");
        } catch (IOException ioEx){
            ioEx.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Map<String, String> getHibernatePropertiesMap() throws IOException {
        return PropertiesManager.getInstance().getPropertiesMap(getHibernateProperties());
    }

}
