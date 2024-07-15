package com.alten.producttrialfeat.factory;

import com.alten.producttrialfeat.dto.ProductDTO;
import com.alten.producttrialfeat.entity.Product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductFactory {
    /**
     * Retourne une listes de Product DTO à partir d'une source d'entité Product.
     *
     * @param products LMes sources de produits
     * @return La liste de DTO.
     */
    public Set<ProductDTO> mapProductsToProductDtos(List<Product> products) {
        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toSet());
    }

    /**
     * Retourne un DTO à partir d'une entité source product.
     *
     * @param product La source.
     * @return Le DTO
     */
    public ProductDTO mapProductToProductDto(Product product) {
        return new ProductDTO(product);
    }

}
