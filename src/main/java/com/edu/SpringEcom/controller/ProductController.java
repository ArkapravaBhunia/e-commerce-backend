package com.edu.SpringEcom.controller;

import com.edu.SpringEcom.model.Product;
import com.edu.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * REST controller for product management operations.
 * 
 * <p>
 * Provides endpoints for:
 * <ul>
 * <li>Product CRUD operations</li>
 * <li>Image upload and retrieval</li>
 * <li>Product search functionality</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <b>Base Path:</b> {@code /api}
 * </p>
 * <p>
 * <b>CORS:</b> Enabled for cross-origin requests
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see ProductService
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products from the catalog.
     * 
     * @return {@link ResponseEntity} containing list of all products with HTTP 200
     */
    @GetMapping("products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    /**
     * Retrieves a single product by its ID.
     * 
     * @param id the unique identifier of the product
     * @return {@link ResponseEntity} with product and HTTP 200, or HTTP 404 if not
     *         found
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);

        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a new product with image upload.
     * 
     * @param product   the product details as JSON in multipart request
     * @param imageFile the product image file
     * @return {@link ResponseEntity} with saved product and HTTP 201, or HTTP 500
     *         on error
     */
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product savedProduct = null;
        try {
            savedProduct = productService.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves the image data for a product.
     * 
     * @param productId the unique identifier of the product
     * @return {@link ResponseEntity} with image bytes and HTTP 200, or HTTP 404 if
     *         not found
     */
    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByproductId(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        if (product.getId() > 0)
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Updates an existing product with new details and image.
     * 
     * @param id        the unique identifier of the product to update
     * @param product   the updated product details
     * @param imageFile the new product image file
     * @return {@link ResponseEntity} with confirmation message and HTTP 200, or
     *         HTTP 400 on error
     */
    @PutMapping("product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
            @RequestPart MultipartFile imageFile) {
        Product updateProduct = null;
        try {
            updateProduct = productService.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>("Update", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a product from the catalog.
     * 
     * @param id the unique identifier of the product to delete
     * @return {@link ResponseEntity} with confirmation message and HTTP 200, or
     *         HTTP 404 if not found
     */
    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Delete", HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Searches for products matching the given keyword.
     * 
     * <p>
     * Searches across product title, description, brand, and category.
     * </p>
     * 
     * @param keyword the search term to match
     * @return {@link ResponseEntity} containing matching products with HTTP 200
     */
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("search with : " + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
