package com.edu.SpringEcom.model.dto;

/**
 * Data Transfer Object representing a single item in an order request.
 * 
 * <p>
 * Used within {@link OrderRequest} to specify which product
 * and how many units to include in the order.
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see OrderRequest
 */
public record OrderItemRequest(
                /** Unique identifier of the product to order. */
                int productId,

                /** Number of units to order. */
                int quantity) {
}
