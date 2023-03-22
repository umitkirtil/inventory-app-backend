package com.inventory;

import com.inventory.entity.Product;
import com.inventory.entity.Warehouse;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.WarehouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class InventoryApplicationTests {

	//@Mock
	ProductRepository productRepository;
	WarehouseRepository warehouseRepository;

	InventoryApplicationTests(@Autowired ProductRepository productRepository , @Autowired WarehouseRepository warehouseRepository) {
		this.productRepository = productRepository;
		this.warehouseRepository = warehouseRepository;
	}

	@BeforeAll
	void firstRunThisToMakeDummyDB() {
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
	}

	@Test
	void realFindTest() {
		List<Warehouse> foundWareHouse = productRepository.findListOfWarehousesByProductName("Kalem1");
		System.out.println("Kalem'in  WareHouse'u [ADI] : " + foundWareHouse.get(0).getName());

		Assertions.assertEquals("Canakkale Deposu - 1" , foundWareHouse.get(0).getName());
	}

}
