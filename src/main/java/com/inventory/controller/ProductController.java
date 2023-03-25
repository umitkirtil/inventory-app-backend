package com.inventory.controller;

import com.inventory.dto.ProductDTO;
import com.inventory.entity.Product;
import com.inventory.entity.Warehouse;
import com.inventory.request.ProductRequest;
import com.inventory.response.ProductResponse;
import com.inventory.service.ProductService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @GetMapping("/warehouses/")
    public ResponseEntity<List<String>> getWhichWarehouseContainsProductByName(@Param("name") String name){
        List<Warehouse> warehouseList = productService.getWarehousesByProductName(name);

        //DTO  , DozerMapper vs de kullanabiliriz. Proje suresi kisaligindan kullanmadim.
        List<String> warehouseNames = warehouseList.stream().map(Warehouse::getName).collect(Collectors.toList());

        return warehouseNames.size() > 0 ? ResponseEntity.ok(warehouseNames) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create/product/")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){

        // product will not have id.
        Product product = dozerBeanMapper.map(productRequest , Product.class);

        // now have and id.
        product = productService.saveProduct(product);

        // we dont want to show it to the user.
        ProductResponse response = dozerBeanMapper.map(product , ProductResponse.class);

        // ok. status 200 ile bir depo urunun bulundugu depo adlarını dondurmus olduk.
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id) ? ResponseEntity.ok(id) : ResponseEntity.notFound().build();
    }

}
