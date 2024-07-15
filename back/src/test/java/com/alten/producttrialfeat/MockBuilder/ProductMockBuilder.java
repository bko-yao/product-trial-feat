package com.alten.producttrialfeat.MockBuilder;

import com.alten.producttrialfeat.entity.Category;
import com.alten.producttrialfeat.entity.Product;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductMockBuilder {
    private long id;
    private String code;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String inventoryStatus;
    private String image;
    private Integer rating;
    private Category category;

    public ProductMockBuilder() {
    }

    public Product build() {
        Product mock = mock(Product.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getCode()).thenReturn(code);
        when(mock.getName()).thenReturn(name);
        when(mock.getDescription()).thenReturn(description);
        when(mock.getCategory()).thenReturn(category);
        when(mock.getPrice()).thenReturn(price);
        when(mock.getQuantity()).thenReturn(quantity);
        when(mock.getInventoryStatus()).thenReturn(inventoryStatus);
        when(mock.getImage()).thenReturn(image);
        when(mock.getRating()).thenReturn(rating);
        return mock;
    }

    public ProductMockBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ProductMockBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public ProductMockBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductMockBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductMockBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public ProductMockBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductMockBuilder setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
        return this;
    }

    public ProductMockBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public ProductMockBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ProductMockBuilder setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
}
