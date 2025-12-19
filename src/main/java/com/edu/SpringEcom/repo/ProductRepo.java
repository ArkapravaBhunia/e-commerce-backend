package com.edu.SpringEcom.repo;

import com.edu.SpringEcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Product} entity database operations.
 * 
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations:
 * <ul>
 * <li>{@code findAll()} - Retrieve all products</li>
 * <li>{@code findById(id)} - Find product by ID</li>
 * <li>{@code save(product)} - Create or update product</li>
 * <li>{@code deleteById(id)} - Delete product by ID</li>
 * </ul>
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see Product
 * @see JpaRepository
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    /**
     * Searches for products matching the given keyword.
     * 
     * <p>
     * Performs a case-insensitive partial match search across:
     * <ul>
     * <li>Product title</li>
     * <li>Product description</li>
     * <li>Brand name</li>
     * <li>Category</li>
     * </ul>
     * </p>
     * 
     * @param keyword the search term to match against product fields
     * @return {@link List} of {@link Product} entities matching the criteria
     */
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
}
