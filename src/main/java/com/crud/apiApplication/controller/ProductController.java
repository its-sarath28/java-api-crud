package com.crud.apiApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;

import com.crud.apiApplication.exception.ProductNotFoundException;
import com.crud.apiApplication.model.Product;
import com.crud.apiApplication.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3002")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add-product")
    public Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @GetMapping("/list-products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }
    
    @PutMapping("/update-product/{productId}")
    public Product UpdateProduct(@RequestBody Product newProduct, @PathVariable Long productId) {
        return productRepository.findById(productId).map(product -> {
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());
            product.setExpirydate(newProduct.getExpirydate());

            return productRepository.save(product);
        }).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @DeleteMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }

        productRepository.deleteById(productId);

        return "Successfully deleted product " + productId;
    }
    
    @GetMapping("/search-products")
    public List<Product> getAllProductsBySearch(Model model, @Param("keyword") String keyword) {
        Iterable<Product> products = productRepository.findAll(keyword);

        model.addAttribute("products", products);
        
        return productRepository.findAll(keyword);
    }
}
