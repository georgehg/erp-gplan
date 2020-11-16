package com.ghgs.erp.storage.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghgs.erp.storage.ShopStorageTestConfig;
import com.ghgs.erp.storage.repository.ClienteRepository;
import com.ghgs.erp.storage.repository.CompraRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = {ShopStorageService.class, ShopStorageTestConfig.class})
public class ShopStorageServiceTest {

  private static final String USER_DIR = System.getProperty("user.dir");
  private static final File JSON_RESPONSE_FILE = new File(USER_DIR + "/src/test/resources/json/random-shop-response.json");

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private CompraRepository compraRepository;

  @Autowired
  private ShopStorageService shopStorageService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void shouldSaveCompras() throws IOException {
    List<CompraDto> compraDtos = objectMapper.readValue(JSON_RESPONSE_FILE, new TypeReference<List<CompraDto>>() {
    });

    shopStorageService.armazenaCompra(compraDtos);

    assertEquals(7, clienteRepository.count());
    assertEquals("Marcus", clienteRepository.findById(9).get().getNome());

    assertEquals(10, compraRepository.count());
    assertEquals((Integer) 5, compraRepository.findById(9L).get().getQtyItens());
    assertEquals((Integer) 10, compraRepository.findById(8L).get().getCliente().getId());
  }

}
