package service.services.properties;

import service.managers.PropertiesManager;

import java.io.IOException;
import java.util.Properties;

public final class HibernateProperties {

    @Deprecated
    private HibernateProperties() {}

    public static Properties getHibernateProperties() {
        try{ return new PropertiesManager().loadProperties("./properties/persistence.properties.txt");
        } catch (IOException ioEx){
            ioEx.printStackTrace();
        }
        return null;
    }
}
