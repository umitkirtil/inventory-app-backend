package com.inventory.service;

import com.inventory.entity.Warehouse;
import com.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Warehouse> getWarehousesByProductName(String productName){
        return productRepository.findListOfWarehousesByProductName(productName);
    }

}
