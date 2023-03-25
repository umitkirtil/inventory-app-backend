package com.inventory.config;

import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    DozerBeanMapper dozerBeanMapper() {
        return new DozerBeanMapper();
    }
}
