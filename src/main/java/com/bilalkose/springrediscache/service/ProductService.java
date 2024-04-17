package com.bilalkose.springrediscache.service;

import com.bilalkose.springrediscache.model.Product;
import com.bilalkose.springrediscache.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow( () -> new RuntimeException("Product not found"));
    }

    @Cacheable(cacheNames = "mySpecialCache")
    public String longRunningMethod() throws InterruptedException {
        Thread.sleep(5000L);
        return "Method çalıştı";
    }

    @CacheEvict(cacheNames = "mySpecialCache")
    public void clearCache() {
        System.out.println("Cache Temizlendi!");
    }

}
