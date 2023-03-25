package com.inventory.config;

import com.inventory.aspect.HistoryLoggerAspect;
import com.inventory.service.HistoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {HistoryService.class})
public class AspectConfig {

    @Bean
    public HistoryLoggerAspect aspect(){
        return new HistoryLoggerAspect();
    }
}
