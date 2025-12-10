package com.edu.SpringEcom.controller;

import com.edu.SpringEcom.model.Product;
import com.edu.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);

        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product savedProduct = null;
        try {
            savedProduct = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/product")
//    public ResponseEntity<?> addProduct(@RequestPart("product") String productJson,
//                                        @RequestPart("imageFile") MultipartFile imageFile) {
//        try {
//            // 1. Manually convert the JSON string to the Product object
//            // This allows us to catch specific formatting errors (like Date issues) inside the code
//            ObjectMapper mapper = new ObjectMapper();
//            Product product = mapper.readValue(productJson, Product.class);
//
//            // 2. Pass to service
//            Product savedProduct = productService.addProduct(product, imageFile);
//            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//
//        } catch (Exception e) {
//            // If the JSON is invalid or Date format is wrong, this error will now appear in Postman
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }


}
