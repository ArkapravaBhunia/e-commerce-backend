package com.edu.SpringEcom.controller;

import com.edu.SpringEcom.model.dto.OrderRequest;
import com.edu.SpringEcom.model.dto.OrderResponse;
import com.edu.SpringEcom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for order management operations.
 * 
 * <p>
 * Provides endpoints for:
 * <ul>
 * <li>Placing new orders</li>
 * <li>Retrieving order history</li>
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
 * @see OrderService
 * @see OrderRequest
 * @see OrderResponse
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Places a new order in the system.
     * 
     * <p>
     * Accepts order details in the request body, processes the order,
     * updates inventory, and returns the created order details.
     * </p>
     * 
     * @param orderRequest the order details including customer info and items
     * @return {@link ResponseEntity} containing created order with HTTP 201
     * @see OrderRequest
     * @see OrderResponse
     */
    @PostMapping("/orders/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    /**
     * Retrieves all orders from the system.
     * 
     * @return {@link ResponseEntity} containing list of all orders with HTTP 200
     */
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> response = orderService.getAllOrderResponses();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
