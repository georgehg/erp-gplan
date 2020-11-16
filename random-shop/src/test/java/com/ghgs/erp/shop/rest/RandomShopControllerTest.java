package com.ghgs.erp.shop.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class RandomShopControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void shouldReturn_RandomShopsList() throws Exception {
    mvc.perform(get("/api/v1/random-shop/compras?quantidade={quantidade}", 10)
        .contextPath("/api/v1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(10)))
        .andExpect(jsonPath("$[0].cliente.id", greaterThan(0)))
        .andExpect(jsonPath("$[0].cliente.nome", notNullValue()))
        .andExpect(jsonPath("$[0].valor", greaterThan(0.0)))
        .andExpect(jsonPath("$[0].qtyItens", greaterThan(0)))
        .andExpect(jsonPath("$[9].cliente.id", greaterThan(0)))
        .andExpect(jsonPath("$[9].cliente.nome", notNullValue()))
        .andExpect(jsonPath("$[9].valor", greaterThan(0.0)))
        .andExpect(jsonPath("$[9].qtyItens", greaterThan(0)));
  }

}
