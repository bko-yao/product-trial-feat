package com.alten.producttrialfeat.services;

import com.alten.producttrialfeat.dto.ProductDTO;
import com.alten.producttrialfeat.entity.Category;
import com.alten.producttrialfeat.entity.Product;
import com.alten.producttrialfeat.exception.CategoryException;
import com.alten.producttrialfeat.exception.ProductException;
import com.alten.producttrialfeat.factory.ProductFactory;
import com.alten.producttrialfeat.repository.CategoryRepository;
import com.alten.producttrialfeat.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository, ProductFactory productFactory) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }

    /**
     * Crée un produit
     *
     * @param productDTO Le produit Dto.
     * @return Le statut de la requête.
     */
    @Transactional
    public ResponseEntity<?> createProduct(ProductDTO productDTO) {

        Category category = this.categoryRepository.findByCode(productDTO.getCategory().toUpperCase().trim())
                .orElseThrow(() -> new CategoryException("La Catégorie " + productDTO.getCategory().toUpperCase().trim() + " n'existe pas.", NOT_FOUND.value()));

        Product product = new Product(productDTO.getCode(), productDTO.getName(), productDTO.getDescription(),
                productDTO.getPrice(), productDTO.getQuantity(), productDTO.getInventoryStatus(),
                productDTO.getImage(), productDTO.getRating(), category);

        this.productRepository.save(product);

        return ResponseEntity.ok()
                .body("Produit enregistré avec succès.");
    }

    /**
     * Recupère tous les produits.
     *
     * @return Une liste de Produit.
     */
    @Transactional(readOnly = true)
    public Set<ProductDTO> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return this.productFactory.mapProductsToProductDtos(products);
    }

    /**
     * Recupère un produit par son id
     *
     * @return le produit s'il existe.
     */
    @Transactional
    public ProductDTO getProductById(long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Le Produit avec l'id " + id + " n'existe pas.", NOT_FOUND.value()));
        return this.productFactory.mapProductToProductDto(product);
    }

    /**
     * Modifie le produit s'il existe.
     *
     * @param id identifiant du produit à modifier
     * @return Le statut de la requête.
     */
    @Transactional
    public ProductDTO updateProduct(long id, Map<String, Object> fields) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Le Produit avec l'id " + id + " n'existe pas.", NOT_FOUND.value()));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Product.class, key);
            switch (key) {
                case "category" -> {
                    Object finalValue = value.toString();
                    value = this.categoryRepository.findByCode(value.toString().toUpperCase().trim())
                            .orElseThrow(() -> new CategoryException("La Catégorie " + finalValue + " n'existe pas.", NOT_FOUND.value()));
                }
                case "price", "quantity" -> value = Integer.parseInt(value.toString());
                default -> {
                }
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingProduct, value);
        });
        return this.productFactory.mapProductToProductDto(
                productRepository.save(existingProduct));
    }

    /**
     * Supprimme le produit.
     *
     * @param id Identifiant du produiot à supprimer.
     */
    @Transactional
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
