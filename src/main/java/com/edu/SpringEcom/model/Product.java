package com.edu.SpringEcom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity class representing a product in the e-commerce catalog.
 * 
 * <p>
 * This entity maps to the database table and contains:
 * <ul>
 * <li>Product identification and basic details</li>
 * <li>Pricing information using {@link BigDecimal} for precision</li>
 * <li>Inventory management fields</li>
 * <li>Product image storage as binary data (BLOB)</li>
 * </ul>
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * Auto-generated primary key identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Product display title. */
    private String title;

    /** Detailed product description. */
    private String description;

    /** Brand or manufacturer name. */
    private String brand;

    /**
     * Product price using BigDecimal for precise monetary calculations.
     * Avoids floating-point precision issues.
     */
    private BigDecimal price;

    /** Product category for classification. */
    private String category;

    /** Product release date formatted as dd-MM-yyyy in JSON responses. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date releaseDate;

    /** Record creation date formatted as dd-MM-yyyy in JSON responses. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createDate;

    /** Flag indicating if product is available for purchase. */
    private boolean availability;

    /** Current inventory stock quantity. */
    private int stockQuantity;

    /** Original filename of the uploaded product image. */
    private String imageName;

    /** MIME type of the product image (e.g., "image/jpeg"). */
    private String imageType;

    /**
     * Binary image data stored as Large Object (BLOB).
     * Consider external storage for production environments.
     */
    @Lob
    private byte[] imageData;

}
