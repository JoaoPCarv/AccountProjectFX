package app;

import model.repository.DAOs.AccountDAO;
import service.properties.DateTimeProperties;
import service.services.datetime.DateTimeService;
import service.services.serializing.SerializingService;
import service.utils.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        //System.out.println(AccountDAO.getInstance("AccountProject").readEntry(Account.class, 1));

        AccountDAO.getInstance("AccountProject").selectAll().forEach(
                acc -> {
                    try {
                        SerializingService.serializeBean(acc, DateTimeService.DEFAULT, StringUtils.NOT_APPLICABLE);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}