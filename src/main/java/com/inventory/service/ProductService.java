package com.inventory.service;

import com.inventory.entity.History;
import com.inventory.entity.ProcessType;
import com.inventory.entity.Product;
import com.inventory.entity.Warehouse;
import com.inventory.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    HistoryService historyService;

    public List<Warehouse> getWarehousesByProductName(String productName){
        return productRepository.findListOfWarehousesByProductName(productName);
    }

    public Product saveProduct(Product product){
        historyService.saveHistory(History.builder().
                processType(ProcessType.EKLENDI).
                category(product.getCategory()).
                warehouse(product.getWarehouseList().stream().reduce((warehouse, warehouse2) -> warehouse2).orElse(null)).
                product(product).
                build());

        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Product product){

        Optional<Product> productFound = productRepository.findById(product.getId());

        if (productFound.isPresent()){

            historyService.saveHistory(History.builder().
                    processType(ProcessType.GUNCELLENDI).
                    category(product.getCategory()).
                    warehouse(product.getWarehouseList().stream().reduce((warehouse, warehouse2) -> warehouse2).orElse(null)).
                    product(product).
                    build());

            productRepository.save(product);
            return productFound;
        }
        else {
            return productFound;
        }


    }

    /**
     * @Param product To Delete
     * return True if deletion successful otherwise false.
     * */
    public boolean deleteProduct(Integer productID){

        Optional<Product> product = productRepository.findById(productID);

        productRepository.deleteById(productID);

        // add history.
        historyService.saveHistory(History.builder().
                processType(ProcessType.EKLENDI).
                category(product.get().getCategory()).
                warehouse(product.get().getWarehouseList().stream().reduce((warehouse, warehouse2) -> warehouse2).orElse(null)).
                product(product.get()).
                build());

        checkProductQuantity(product.get());

        // if null deleteSuccess so return true.
        return product.orElse(null) == null ? true : false;
    }

    private void checkProductQuantity(Product product) {
        if (product.getQuantity() < product.getMinimumQuantity()){
            // Mail gonderimi de cok farklı degil.
            log.warn("Urun sayisi esik degeri altina dusmustur : {} :: Kalan urun sayisi : {}" , product.getName() , product.getQuantity());
        }
    }

}
