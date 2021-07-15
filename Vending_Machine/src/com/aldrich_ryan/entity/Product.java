package com.aldrich_ryan.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {
    private String idproduct;
    private String nameproduct;
    private int price;
    private int stock;
    private String image;

    @Id
    @Column(name = "idproduct", nullable = false, length = 10)
    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    @Basic
    @Column(name = "nameproduct", nullable = false, length = 50)
    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "stock", nullable = false)
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "image", nullable = false, length = 50)
    public String getImage() { return image; }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && stock == product.stock && Objects.equals(idproduct, product.idproduct) && Objects.equals(nameproduct, product.nameproduct) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, nameproduct, price, stock, image);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idproduct='" + idproduct + '\'' +
                ", nameproduct='" + nameproduct + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", image='" + image + '\'' +
                '}';
    }
}
