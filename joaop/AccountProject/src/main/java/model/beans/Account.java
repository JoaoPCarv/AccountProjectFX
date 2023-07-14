package model.beans;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAcc")
    private int id;

    @Column(name = "nameAcc")
    private String name;

    @Column(name = "emailAcc")
    private String email;

    @Column(name = "passwordAcc")
    private String password;

    @Column(name = "imageAcc")
    private byte[] image;

    public Account() {}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
