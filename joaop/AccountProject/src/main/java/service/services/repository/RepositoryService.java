package service.services.repository;

import service.exceptions.InvalidArgumentException;
import service.managers.PropertiesManager;
import service.properties.HibernateProperties;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;

public final class RepositoryService {

    @Deprecated(since = "1.0")
    private RepositoryService() { /* This class should not be instantiated. */ }

    public static <T extends Object> void validateClassAsATable(Class<T> clazz) throws InvalidArgumentException {
        if(!clazz.isAnnotationPresent(javax.persistence.Table.class)) {
            throw new InvalidArgumentException("Table", clazz.getSimpleName());
        }
    }

    public static EntityManager startEntityManager(String persistenceUnitName) throws IOException {
        return Persistence.createEntityManagerFactory(persistenceUnitName,
                new PropertiesManager().getPropertiesMap(HibernateProperties.getHibernateProperties()))
                .createEntityManager();
    }

}
