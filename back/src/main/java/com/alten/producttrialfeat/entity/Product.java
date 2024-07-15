package com.alten.producttrialfeat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

/**
 * Entité de Produit
 */
@Entity
@Table(name = Product.TABLE_NAME)
public class Product extends AbstractEntity {
    public static final String TABLE_NAME = "product";

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "inventory_status")
    private String inventoryStatus;
    @Column(name = "image")
    private String image;
    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Category.TABLE_ID)
    private Category category;

    /**
     * Constructeur par défaut.
     */
    public Product() {
    }

    public Product(String code, String name, String description,
                   int price, int quantity, String inventoryStatus,
                   String image, Integer rating, Category category) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.inventoryStatus = inventoryStatus;
        this.image = image;
        this.rating = rating;
        this.category = category;
    }

    /**
     * Retourne la désignation de la catégorie du produit.
     */
    public String designationCategory() {
        return getCategory() != null ? getCategory().getDesignation() : null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public Category getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public Integer getRating() {
        return rating;
    }
}
