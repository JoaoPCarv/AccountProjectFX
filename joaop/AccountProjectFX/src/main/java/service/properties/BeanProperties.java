package service.properties;

import model.beans.Account;
import model.beans.Bean;
import service.managers.PropertiesManager;
import service.services.serializing.SerializingService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public final class BeanProperties {

    @Deprecated(since = "1.0")
    private BeanProperties() { /* This class should not be instantiated. */ }

    public static <B extends Bean>  String getBeanDirectoryPath(Class<B> bean) throws IOException, URISyntaxException {

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(String.format("model.beans.%s.dir.path", bean.getSimpleName()));

        return PropertiesManager.getInstance().loadPropertiesFromResourceName("/serializing.properties")
                .getProperty(sBuilder.toString());
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(PropertiesManager.getInstance().loadPropertiesFromResourceName(""));
    }

}