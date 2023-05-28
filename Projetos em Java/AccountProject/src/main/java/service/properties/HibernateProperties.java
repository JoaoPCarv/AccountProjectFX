package service.properties;

import service.managers.PropertiesManager;

import java.io.IOException;
import java.util.Properties;

public final class HibernateProperties {

    @Deprecated(since = "1.0")
    private HibernateProperties() { /* This class should not be instantiated. */ }

    public static Properties getHibernateProperties() {
        try{ return new PropertiesManager().loadProperties("./properties/persistence.properties");
        } catch (IOException ioEx){
            ioEx.printStackTrace();
        }
        return null;
    }
}
