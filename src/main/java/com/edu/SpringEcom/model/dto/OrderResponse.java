package com.edu.SpringEcom.model.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for order responses.
 * 
 * <p>
 * This record is returned by the API after placing or retrieving orders.
 * It contains complete order details including all line items.
 * </p>
 * 
 * <p>
 * <b>Example JSON Response:</b>
 * </p>
 * 
 * <pre>
 * {
 *   "orderId": "A1B2C3D4",
 *   "customerName": "John Doe",
 *   "email": "john@example.com",
 *   "status": "PLACED",
 *   "orderDate": "2025-12-14",
 *   "items": [...]
 * }
 * </pre>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see OrderItemResponse
 * @see com.edu.SpringEcom.service.OrderService#placeOrder
 */
public record OrderResponse(
        /** Unique 8-character order identifier for customer reference. */
        String orderId,

        /** Customer's full name. */
        String customerName,

        /** Customer's email address. */
        String email,

        /** Current order status (e.g., PLACED, SHIPPED, DELIVERED). */
        String status,

        /** Date when the order was placed. */
        LocalDate orderDate,

        /** List of items included in this order. */
        List<OrderItemResponse> items) {
}
