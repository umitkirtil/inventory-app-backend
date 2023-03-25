package com.inventory.request;

import com.inventory.entity.Category;
import com.inventory.entity.Warehouse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductRequest {

    private String name;

    private Integer quantity;

    private Integer minimumQuantity;

    private Category category;

    private List<Warehouse> warehouseList = new ArrayList<>();
}
