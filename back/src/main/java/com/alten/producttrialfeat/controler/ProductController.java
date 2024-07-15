package com.alten.producttrialfeat.controler;

import com.alten.producttrialfeat.dto.ProductDTO;
import com.alten.producttrialfeat.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Crée un nouveau produit
     *
     * @param productDTO Le DTO du produit.
     * @return Le statut de la requête.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    /**
     * Recupère tous les produits.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Récupère le produit par son id.
     *
     * @param id Identifiant du produit.
     * @return Le produit s'il existe.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO getAllProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    /**
     * Modifie partiellement un produit.
     *
     * @param id     L'identifiant du produit à modifier
     * @param fields Les champs à modifier
     * @return Le produit modifié.
     */
    @PatchMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return productService.updateProduct(id, fields);
    }

    /**
     * Supprime le produit par son id.
     *
     * @param id Identifiant du produit.
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
