package com.edu.SpringEcom.repo;

import com.edu.SpringEcom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link Order} entity database operations.
 * 
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 * Includes custom query method for finding orders by order ID string.
 * </p>
 * 
 * <p>
 * <b>Note:</b> Generic type uses Integer but Order.id is Long.
 * Consider changing to {@code JpaRepository<Order, Long>} for consistency.
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see Order
 * @see JpaRepository
 */
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    /**
     * Finds an order by its unique order ID string.
     * 
     * <p>
     * Spring Data JPA automatically implements this method based on
     * the naming convention {@code findBy<FieldName>}.
     * </p>
     * 
     * @param orderId the unique order identifier (e.g., "A1B2C3D4")
     * @return {@link Optional} containing the order if found, empty otherwise
     */
    Optional<Order> findByOrderId(String orderId);
}
