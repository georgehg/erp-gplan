package com.ghgs.erp.storage.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghgs.erp.storage.ShopStorageTestConfig;
import com.ghgs.erp.storage.repository.ClienteRepository;
import com.ghgs.erp.storage.repository.CompraRepository;
import com.ghgs.erp.storage.repository.GastoClienteDto;
import com.ghgs.erp.storage.repository.VendasPorDataDto;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = {ShopStorageHandler.class, ShopStorageTestConfig.class})
public class ShopStorageHandlerTest {

  private static final String USER_DIR = System.getProperty("user.dir");
  private static final File JSON_INPUT_FILE = new File(USER_DIR + "/src/test/resources/json/random-shop-response.json");

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private CompraRepository compraRepository;

  @Autowired
  private ShopStorageHandler shopStorageHandler;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void populateDatabase() throws IOException {
    List<CompraDto> compraDtos = objectMapper.readValue(JSON_INPUT_FILE, new TypeReference<List<CompraDto>>() {
    });

    shopStorageHandler.armazenaCompras(compraDtos);
  }

  @After
  public void cleanDataBase() {
    compraRepository.deleteAll();
    clienteRepository.deleteAll();
  }

  @Test
  public void givenRandomShops_shouldStoreClientsAndShops() {
    assertEquals(7, clienteRepository.count());
    assertEquals("Marcus", clienteRepository.findById(9).get().getNome());

    assertEquals(10, compraRepository.count());
    assertEquals((Integer) 5, compraRepository.findById(9L).get().getQtyItens());
    assertEquals((Integer) 10, compraRepository.findById(8L).get().getCliente().getId());
  }

  @Test
  public void givenRandomShops_shouldReturnTotalClientSpending() {
    List<GastoClienteDto> totalGastoCliente = shopStorageHandler.getTotalGastoCliente();

    assertEquals(7, totalGastoCliente.size());
    assertEquals((Double) 74.69, (Double) (Math.round(totalGastoCliente.get(0).getValor() * 100.0) / 100.0));
    assertEquals((Double) 143.29, (Double) (Math.round(totalGastoCliente.get(1).getValor() * 100.0) / 100.0));
    assertEquals((Double) 54.29, (Double) (Math.round(totalGastoCliente.get(6).getValor() * 100.0) / 100.0));
  }

  @Test
  public void givenRandomShops_shouldReturnSellsQty() {
    List<VendasPorDataDto> totalVendas = shopStorageHandler.getQuantidadeVendas();

    assertEquals(6, totalVendas.size());
    assertEquals("2020-11-16", totalVendas.get(0).getData().toString());
    assertEquals((Long) 6l, totalVendas.get(0).getQtyItens());
    assertEquals("2020-11-21", totalVendas.get(4).getData().toString());
    assertEquals((Long) 10l, totalVendas.get(4).getQtyItens());
  }

}
