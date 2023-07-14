package model.beans;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProd")
    private int id;

    @Column(name = "nameProd")
    private String name;

    @Column(name = "catProd")
    private String category;

    @Column(name = "valueProd")
    private double value;

    @Column(name = "imageProd")
    private byte[] image;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public double getValue() {
        return this.value;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product() {}

    public Product(String name, String category, double value, byte[] image) {
        this.setName(name);
        this.setCategory(category);
        this.setValue(value);
        this.setImage(image);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        StringBuffer sBuilder = new StringBuffer();
        sBuilder.append("*| ID: ");
        sBuilder.append(this.getId());
        sBuilder.append(" | Name: ");
        sBuilder.append(this.getName());
        sBuilder.append(" | Category: ");
        sBuilder.append(this.getCategory());
        sBuilder.append(" | Value: ");
        sBuilder.append(this.getValue());
        sBuilder.append(" *|");

        return sBuilder.toString();
    }

}
