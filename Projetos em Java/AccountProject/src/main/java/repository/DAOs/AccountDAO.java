package repository.DAOs;

import model.beans.Account;

import java.io.IOException;

public class AccountDAO extends BeanDAO<Account> {
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
}
