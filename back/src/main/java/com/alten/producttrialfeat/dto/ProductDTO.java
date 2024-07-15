package com.alten.producttrialfeat.dto;

import com.alten.producttrialfeat.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO de product
 */
public class ProductDTO {
    private long id;
    @NotBlank (message = "Le code doit être renseigné")
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Min(0)
    private int price;
    @Min(0)
    private int quantity;
    @NotBlank
    private String inventoryStatus;
    @NotBlank
    private String category;
    private String image;
    private Integer rating;

    /**
     * Constructeur par défaut
     */
    public ProductDTO() {

    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.code = product.getCode();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.inventoryStatus = product.getInventoryStatus();
        this.category = product.designationCategory();
        this.image = product.getImage();
        this.rating = product.getRating();
    }

    public long getId() {
        return id;
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

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public Integer getRating() {
        return rating;
    }
}