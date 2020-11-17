package com.ghgs.erp.bots.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghgs.erp.bots.ApiClientConfig;
import com.ghgs.erp.bots.BuyerBotTestConfig;
import com.ghgs.erp.bots.exception.CouldNotPostShopListException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StorageClient.class, ApiClientConfig.class, BuyerBotTestConfig.class})
public class StorageClientTest {

  private static final String USER_DIR = System.getProperty("user.dir");
  private static final File JSON_INPUT_FILE = new File(USER_DIR + "/src/test/resources/json/random-shop-response.json");

  @Autowired
  private StorageClient storageClient;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  private MockRestServiceServer mockServer;

  @Before
  public void init() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void givenListaCompras_ShouldStore() throws URISyntaxException, IOException {
    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:8888/shop-store/compras")))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withStatus(HttpStatus.CREATED));

    List<CompraDto> compraDtos = objectMapper.readValue(JSON_INPUT_FILE, new TypeReference<List<CompraDto>>() {
    });

    storageClient.guardaCompras(compraDtos);
  }

  @Test(expected = CouldNotPostShopListException.class)
  public void shouldReturn_BadRequestError() throws URISyntaxException {
    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:8888/shop-store/compras")))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withStatus(HttpStatus.BAD_REQUEST)
            .body("Invalid Request"));

    storageClient.guardaCompras(null);
  }

  @Test(expected = CouldNotPostShopListException.class)
  public void shouldReturn_InternalServerError() throws URISyntaxException {
    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:8888/shop-store/compras")))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Internal Server Error"));

    storageClient.guardaCompras(null);
  }

}
