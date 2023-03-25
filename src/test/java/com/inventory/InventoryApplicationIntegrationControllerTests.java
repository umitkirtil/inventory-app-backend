package com.inventory;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.inventory.controller.ProductController;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InventoryApplicationIntegrationControllerTests {

    /**
     * dont need spring so excluded springboottests.
     */

    // To Mock MVC
    @Autowired
    private MockMvc mvc;

    @Autowired
    ProductController productController;

    @Test
    void getProductWareHouseTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/warehouses/").queryParam("name", "Kalem 1");
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
        MvcResult result = mvc.perform(request).andReturn();

        assertEquals(404, result.getResponse().getStatus());


        RequestBuilder request2 = MockMvcRequestBuilders.get("/api/warehouses/").queryParam("name", "Kalem1");
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
        MvcResult result2 = mvc.perform(request2).andReturn();

        assertEquals(200, result2.getResponse().getStatus());
    }


}