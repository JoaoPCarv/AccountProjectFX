package main.java;

import java.io.*;
import java.util.Properties;

public final class LoadPropTester {

    @Deprecated
    private LoadPropTester(){}

    public static Properties getProjectProperties() throws IOException {

        Properties properties = new Properties();
        File file = new File("./properties/properties.txt");
        properties.load(new BufferedReader(new FileReader(file)));
        return properties;
    }
}
