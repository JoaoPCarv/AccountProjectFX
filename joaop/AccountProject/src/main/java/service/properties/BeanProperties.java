package service.properties;

import model.beans.Bean;
import service.managers.PropertiesManager;

import java.io.IOException;

public final class BeanProperties {

    @Deprecated(since = "1.0")
    private BeanProperties() { /* This class should not be instantiated. */ }

    public static <B extends Bean>  String getBeanDirectoryPath(Class<B> bean) throws IOException {

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(String.format("model.beans.%s.dir.path", bean.getSimpleName()));

        return new PropertiesManager().loadProperties("./properties/serializing.properties")
                .getProperty(sBuilder.toString());
    }

}
