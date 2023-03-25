package com.inventory.service;

import com.inventory.entity.Product;
import com.inventory.entity.Warehouse;
import com.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Warehouse> getWarehousesByProductName(String productName){
        return productRepository.findListOfWarehousesByProductName(productName);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    /**
     * @Param product To Delete
     * return True if deletion successful otherwise false.
     * */
    public boolean deleteProduct(Integer productID){
        productRepository.deleteById(productID);

        //checkEmailSend();

        // if null deleteSuccess so return true.
        return productRepository.findById(productID).orElse(null) == null ? true : false;
    }

}
