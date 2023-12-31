package com.nyal.minisof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductCartEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productCartId;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private ProductEntity product;

    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public ProductCartEntity() {
    }

    public ProductCartEntity(int productCartId, ProductEntity product, int quantity, double price, AccountEntity account) {
        this.productCartId = productCartId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.account = account;
    }

    public int getProductCartId() {
        return productCartId;
    }

    public void setProductCartId(int productCartId) {
        this.productCartId = productCartId;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
