package com.edu.SpringEcom.model.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object representing a single item in an order response.
 * 
 * <p>
 * Used within {@link OrderResponse} to provide details about
 * each product included in the order.
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see OrderResponse
 */
public record OrderItemResponse(
                /** Display name of the ordered product. */
                String productName,

                /** Number of units ordered. */
                int quantity,

                /** Calculated total price (unit price × quantity). */
                BigDecimal totalPrice) {
}
