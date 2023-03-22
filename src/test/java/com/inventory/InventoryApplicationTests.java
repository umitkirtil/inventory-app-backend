package com.inventory;

import com.inventory.entity.Product;
import com.inventory.entity.Warehouse;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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

	@Test
	void contextLoads() {

		/*Product kalem = new Product();
		kalem.setName("Kalem1");
		kalem.setQuantity(5);

		Warehouse canakkaleDeposu = new Warehouse();
		canakkaleDeposu.setName("Canakkale Deposu - 1");
		canakkaleDeposu.setCity("Canakkale");
		canakkaleDeposu.setState("Marmara");
		canakkaleDeposu.setAddress("Merkez / Canakkale");
		warehouseRepository.save(canakkaleDeposu);

		kalem.getWarehouseList().add(canakkaleDeposu);

		productRepository.save(kalem);*/

		List<Warehouse> foundWareHouse = productRepository.findListOfWarehousesByProductName("Kalem1");

		//System.out.println("Kalem Kayit Edildi : " + kalem.getId());
		System.out.println("Kalem'in  WareHouse'u [ADI] : " + foundWareHouse.get(0).getName());

	}

}
