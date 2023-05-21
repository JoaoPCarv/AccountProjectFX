package service.services.properties;

import model.beans.Account;
import model.beans.Bean;
import service.managers.PropertiesManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public final class BeanProperties {

    @Deprecated
    private BeanProperties() {}

    public static <B extends Bean>  String getBeanDirectoryPath(Class<B> bean) throws IOException {

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(String.format("model.beans.%s.dir.path", bean.getSimpleName()));

        return new PropertiesManager().loadProperties("./properties/serializing.properties.txt")
                .getProperty(sBuilder.toString());
    }

}
