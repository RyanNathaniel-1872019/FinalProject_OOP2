package com.aldrich_ryan.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Transaction {
    private int idtransaction;
    private int price;
    private int qty;
    private Timestamp transactiondate;
    private Product product;

    @Id
    @Column(name = "idtransaction", nullable = false)
    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
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
    @Column(name = "qty", nullable = false)
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "transactiondate", nullable = false)
    public Timestamp getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Timestamp transactiondate) {
        this.transactiondate = transactiondate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return idtransaction == that.idtransaction && Double.compare(that.price, price) == 0 && qty == that.qty && Objects.equals(transactiondate, that.transactiondate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtransaction, price, qty, transactiondate);
    }

    @ManyToOne
    @JoinColumn(name = "product_idproduct", referencedColumnName = "idproduct", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idtransaction=" + idtransaction +
                ", price=" + price +
                ", qty=" + qty +
                ", transactiondate=" + transactiondate +
                ", product=" + product +
                '}';
    }
}
