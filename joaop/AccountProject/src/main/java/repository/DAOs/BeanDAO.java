package repository.DAOs;

import model.beans.Bean;
import repository.interfaces.Repository;
import service.services.repository.RepositoryService;

import javax.persistence.EntityManager;
import java.io.IOException;

public abstract class BeanDAO <B extends Bean> implements Repository<B> {

    private EntityManager entityManager;

    protected BeanDAO(String persistenceUnitName) throws IOException {
        entityManager = RepositoryService.startEntityManager(persistenceUnitName);
    }

    @Override
    public void createEntry(B b) {
        entityManager.getTransaction().begin();
        entityManager.persist(b);
        entityManager.getTransaction().commit();
    }

    @Override
    public B readEntry(Class<B> clazz, int id) {
        entityManager.getTransaction().begin();
        B bean = entityManager.find(clazz, id);
        entityManager.getTransaction().commit();
        return bean;
    }

    @Override
    public void updateEntry(B b) {
        entityManager.getTransaction().begin();
        entityManager.merge(b);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteEntry(B b) {
        entityManager.getTransaction().begin();
        entityManager.remove(b);
        entityManager.getTransaction().commit();
    }
}