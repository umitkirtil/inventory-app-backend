package com.inventory.controller;

import com.inventory.entity.Warehouse;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping(path = "findwarehouses")
    public ResponseEntity<List<String>> getWhichWarehouseContainsProductByName(@RequestBody String name){
        List<Warehouse> warehouseList = productService.getWarehousesByProductName(name);

        //DTO  , DozerMapper vs de kullanabiliriz. Proje suresi kisaligindan kullanmadim.
        List<String> warehouseNames = warehouseList.stream().map(Warehouse::getName).collect(Collectors.toList());

        // ok. status 200 ile bir depo urunun bulundugu depo adlarını dondurmus olduk.
        return  ResponseEntity.ok(warehouseNames);
    }

}
