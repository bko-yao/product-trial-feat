package com.alten.producttrialfeat.MockBuilder;

import com.alten.producttrialfeat.dto.ProductDTO;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDtoMockBuilder {
    private long id;
    private String code;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String inventoryStatus;
    private String category;
    private String image;
    private Integer rating;

    public ProductDtoMockBuilder() {
    }

    public ProductDTO build() {
        ProductDTO mock = mock(ProductDTO.class);
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

    public ProductDtoMockBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ProductDtoMockBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public ProductDtoMockBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductDtoMockBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDtoMockBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public ProductDtoMockBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductDtoMockBuilder setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
        return this;
    }

    public ProductDtoMockBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductDtoMockBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ProductDtoMockBuilder setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
}
