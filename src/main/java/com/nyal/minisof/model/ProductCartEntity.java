package com.nyal.minisof.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductCartEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productCartId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private int quantity;
    private double price;

    public ProductCartEntity() {
    }

    public ProductCartEntity(int productCartId, ProductEntity product, int quantity, double price) {
        this.productCartId = productCartId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
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
}
