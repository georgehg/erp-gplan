package com.ghgs.erp.storage.rest;

import com.ghgs.erp.storage.domain.CompraDto;
import com.ghgs.erp.storage.domain.ShopStorageHandler;
import com.ghgs.erp.storage.repository.GastoClienteDto;
import com.ghgs.erp.storage.repository.VendasPorDataDto;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShopStorageController.class)
@AutoConfigureMockMvc
public class ShopStorageControllerTest {

  private static final String USER_DIR = System.getProperty("user.dir");
  private static final File JSON_INPUT_FILE = new File(USER_DIR + "/src/test/resources/json/random-shop-response.json");

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ShopStorageHandler shopStorageHandler;

  @Captor
  private ArgumentCaptor<List<CompraDto>> compraDtoArgumentCaptor;

  @Test
  public void givenRandomShops_ShouldHitCreateComprasEndPoint() throws Exception {
    mvc.perform(post("/api/v1/shop-store/compras")
        .contextPath("/api/v1")
        .content(Files.readAllBytes(JSON_INPUT_FILE.toPath()))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    verify(shopStorageHandler, times(1)).armazenaCompras(compraDtoArgumentCaptor.capture());

    assertEquals(10, compraDtoArgumentCaptor.getValue().size());
  }

  @Test
  public void givenRandomShops_ShouldHitReportClientSpendings() throws Exception {
    when(shopStorageHandler.getTotalGastoCliente()).thenReturn(Arrays.asList(
        new GastoClienteDto(1, "Joao", 100.00),
        new GastoClienteDto(2, "Maria", 200.00)));

    mvc.perform(get("/api/v1/shop-store/reports/clientes")
        .contextPath("/api/v1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].valor", equalTo(100.00)))
        .andExpect(jsonPath("$[1].valor", equalTo(200.00)));

  }

  @Test
  public void givenRandomShops_ShouldHitReportTotalSells() throws Exception {
    LocalDate now = LocalDate.now();
    when(shopStorageHandler.getQuantidadeVendas()).thenReturn(Arrays.asList(
        new VendasPorDataDto(now, 10l),
        new VendasPorDataDto(now.plusDays(3), 20l)));

    mvc.perform(get("/api/v1/shop-store/reports/vendas")
        .contextPath("/api/v1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].data", equalTo(now.toString())))
        .andExpect(jsonPath("$[1].data", equalTo(now.plusDays(3).toString())));
  }

}
