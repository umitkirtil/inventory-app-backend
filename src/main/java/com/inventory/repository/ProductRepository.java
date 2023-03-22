package com.inventory.repository;

import com.inventory.entity.Product;
import com.inventory.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Integer> {

    @Query("select w from Product p join p.warehouseList w where p.name = :productName")
    List<Warehouse> findListOfWarehousesByProductName(@Param("productName") String name);
}
