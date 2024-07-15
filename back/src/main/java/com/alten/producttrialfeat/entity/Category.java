package com.alten.producttrialfeat.entity;

import com.alten.producttrialfeat.util.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entité de catégorie.
 */
@Entity
@Table(name = Category.TABLE_NAME)
public class Category extends AbstractEntity {
    public static final String TABLE_NAME = "category";
    public static final String TABLE_ID = TABLE_NAME + Constant.SUFFIX_ID;

    @Column(name = "code", nullable = false, unique = true, updatable = false)
    private String code;
    @Column(name = "designation")
    private String designation;

    /**
     * Constructeur par défaut.
     */
    public Category() {
    }

    public String getCode() {
        return code;
    }

    public String getDesignation() {
        return designation;
    }
}
