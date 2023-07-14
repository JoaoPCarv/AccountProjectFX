package model.repository.DAOs;

import model.beans.Account;

import java.io.IOException;
import java.util.List;

public class AccountDAO extends BeanDAO<Account> {

    private static AccountDAO instance = null;

    public static AccountDAO getInstance(String persistenceUnitName) throws IOException {
        if(instance == null) {
            instance = new AccountDAO(persistenceUnitName);
        } else {
            instance.flush();
            instance.setEntityManager(persistenceUnitName);
        }
        return instance;
    }

    public AccountDAO(String persistenceUnitName) throws IOException {
        super(persistenceUnitName);
    }

    public void updateName(Account acc, String newName) {
        acc.setName(newName);
        super.updateEntry(acc);
    }

    public void updateEmail(Account acc, String newEmail) {
        acc.setEmail(newEmail);
        super.updateEntry(acc);
    }

    public void updatePassword(Account acc, String newPassword) {
        acc.setPassword(newPassword);
        super.updateEntry(acc);
    }

    public void updateImage(Account acc, byte[] newImage) {
        acc.setImage(newImage);
        super.updateEntry(acc);
    }

    @Override
    public List<Account> selectAll() {
        entityManager.getTransaction().begin();
        return entityManager.createNamedQuery("Account_SelectAll", Account.class).getResultList();
    }
}
