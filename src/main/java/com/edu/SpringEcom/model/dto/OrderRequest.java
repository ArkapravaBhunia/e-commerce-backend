package com.edu.SpringEcom.model.dto;

import java.util.List;

/**
 * Data Transfer Object for creating new orders.
 * 
 * <p>
 * This record is used as the request body when placing orders via the API.
 * It contains customer information and the list of items to be ordered.
 * </p>
 * 
 * <p>
 * <b>Example JSON Request:</b>
 * </p>
 * 
 * <pre>
 * {
 *   "customerName": "John Doe",
 *   "email": "john@example.com",
 *   "items": [
 *     {"productId": 1, "quantity": 2},
 *     {"productId": 3, "quantity": 1}
 *   ]
 * }
 * </pre>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 * @see OrderItemRequest
 * @see com.edu.SpringEcom.service.OrderService#placeOrder(OrderRequest)
 */
public record OrderRequest(
        Long userId,
        Long addressId,
        String couponCode,
        List<OrderItemRequest> items
) {}
