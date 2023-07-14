package app;

import service.exceptions.PropertyNotFoundException;
import service.managers.PropertiesManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        //System.out.println(AccountDAO.getInstance("AccountProject").readEntry(Account.class, 1));

        try {
            System.out.println(PropertiesManager.getInstance().getProperty(
                    PropertiesManager.getInstance()
                            .loadPropertiesFromResourceName("/datetime.properties"), "My error"));
        } catch (IOException | URISyntaxException | PropertyNotFoundException e) {
            Logger.getLogger("Err").log(Level.SEVERE, e.getMessage());
        }
    }
}