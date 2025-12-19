package com.edu.SpringEcom.service;

import com.edu.SpringEcom.model.Product;
import com.edu.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service class responsible for product management operations.
 * 
 * <p>
 * This service provides the business logic layer for:
 * <ul>
 * <li>Product CRUD operations</li>
 * <li>Product image upload and storage</li>
 * <li>Product search functionality</li>
 * </ul>
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see Product
 * @see ProductRepo
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    /**
     * Retrieves all products from the database.
     * 
     * @return {@link List} of all {@link Product} entities in the database
     */
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    /**
     * Retrieves a single product by its unique identifier.
     * 
     * @param id the unique identifier of the product
     * @return the {@link Product} if found, {@code null} otherwise
     */
    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    /**
     * Creates a new product or updates an existing product with image data.
     * 
     * <p>
     * This method handles multipart file upload by:
     * <ol>
     * <li>Extracting the original filename from the uploaded file</li>
     * <li>Determining the content type (MIME type) of the image</li>
     * <li>Converting the image to a byte array for database storage</li>
     * <li>Persisting the complete product entity</li>
     * </ol>
     * </p>
     * 
     * @param product   the product entity containing product details
     * @param imageFile the multipart file containing the product image
     * @return the saved {@link Product} entity with generated ID
     * @throws IOException if the image file cannot be read or processed
     */
    public Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }

    /**
     * Deletes a product from the database.
     * 
     * @param id the unique identifier of the product to delete
     */
    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    /**
     * Searches for products matching the given keyword.
     * 
     * <p>
     * Performs a case-insensitive search across multiple product fields
     * including title, description, brand, and category.
     * </p>
     * 
     * @param keyword the search term to match against product fields
     * @return {@link List} of {@link Product} entities matching the search criteria
     * @see ProductRepo#searchProducts(String)
     */
    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
