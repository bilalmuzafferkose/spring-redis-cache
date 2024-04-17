package com.bilalkose.springrediscache.controller;
import com.bilalkose.springrediscache.model.Product;
import com.bilalkose.springrediscache.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/redis")
public class RedisCacheController {

    private final ProductService productService;
    private int count = 0; // for Testing

    public RedisCacheController(ProductService productService) {
        this.productService = productService;
    }

    @CacheEvict(value = "products", allEntries = true) // Yeni veri eklendiğinde cache temizlensin
    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @Cacheable(cacheNames = "products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/cacheControl")
    public String cacheControl() throws InterruptedException {
        if(count == 5) {
            productService.clearCache();
        }
        count++;
        return productService.longRunningMethod();
    }
}
