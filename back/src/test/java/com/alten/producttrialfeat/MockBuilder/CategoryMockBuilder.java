package com.alten.producttrialfeat.MockBuilder;

import com.alten.producttrialfeat.entity.Category;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryMockBuilder {
    private long id;
    private String code;
    private String designation;

    public CategoryMockBuilder() {
    }

    public Category build() {
        Category mock = mock(Category.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getCode()).thenReturn(code);
        when(mock.getDesignation()).thenReturn(designation);
        return mock;
    }

    public CategoryMockBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public CategoryMockBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public CategoryMockBuilder setDesignation(String designation) {
        this.designation = designation;
        return this;
    }
}
