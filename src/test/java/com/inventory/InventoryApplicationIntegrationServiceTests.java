package com.inventory;

import com.inventory.entity.*;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.WarehouseRepository;
import com.inventory.service.HistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class InventoryApplicationIntegrationServiceTests {

    //@Mock
    @Autowired
    ProductRepository productRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    HistoryService historyService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void realFindTest() {
        Product kalem = new Product();
        kalem.setName("Kalem1");
        kalem.setQuantity(5);

        Warehouse canakkaleDeposu = new Warehouse();
        canakkaleDeposu.setName("Canakkale Deposu - 1");
        canakkaleDeposu.setCity("Canakkale");
        canakkaleDeposu.setState("Marmara");
        canakkaleDeposu.setAddress("Merkez / Canakkale");
        warehouseRepository.save(canakkaleDeposu); // oncesinde kaydetmek zorunda kaldim. Transactional || Cascade ile cozulebilir.

        kalem.getWarehouseList().add(canakkaleDeposu);
        productRepository.save(kalem);

        List<Warehouse> foundWareHouse = productRepository.findListOfWarehousesByProductName("Kalem1");
        System.out.println("Kalem'in  WareHouse'u [ADI] : " + foundWareHouse.get(0).getName());

        Assertions.assertEquals("Canakkale Deposu - 1", foundWareHouse.get(0).getName());
    }

    @Test
    void historyListTest() {
        Category kirtasiye = Category.builder().name("Kırtasiye").productList(Arrays.asList()).build();
        categoryRepository.save(kirtasiye);

        Product kalem = Product.builder().name("Faber Castell Silgi").category(kirtasiye).quantity(5).build();

        Warehouse canakkaleDepo = Warehouse.builder().name("Canakkale Depo 2").state("Merkez / Canakkale").address("Canakkale").city("Canakkale").build();
        warehouseRepository.save(canakkaleDepo);

        kalem.setWarehouseList(Collections.singleton(canakkaleDepo));
        productRepository.save(kalem);

        History history = History.builder().product(kalem).category(kirtasiye).processType(ProcessType.EKLENDI).warehouse(canakkaleDepo).build();
        historyService.saveHistory(history);
    }


}
