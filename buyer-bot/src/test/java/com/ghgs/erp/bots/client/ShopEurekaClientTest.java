/*package com.ghgs.erp.bots.client;

import com.ghgs.erp.bots.ApiClientConfig;
import com.ghgs.erp.bots.exception.CouldNotGetShopListException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShopEurekaClient.class, ApiClientConfig.class})
public class ShopEurekaClientTest {

  private static final String USER_DIR = System.getProperty("user.dir");
  private static final File JSON_RESPONSE_FILE = new File(USER_DIR + "/src/test/resources/json/random-shop-response.json");

  @Autowired
  private ShopEurekaClient shopClient;

  @Autowired
  private RestTemplate restTemplate;

  private MockRestServiceServer mockServer;

  @Before
  public void init() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void shouldReturn_DezCompras() throws IOException, URISyntaxException {
    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:9999/random-shop/compras?quantidade=10")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Files.readAllBytes(JSON_RESPONSE_FILE.toPath())));

    List<CompraDto> compras = shopClient.getCompras(10);

    assertEquals(10, compras.size());
    assertEquals((Integer) 3, compras.get(0).getCliente().getId());
    assertEquals("Pedro", compras.get(0).getCliente().getNome());
    assertEquals("2020-11-16", compras.get(0).getData().toString());
  }

  @Test(expected = CouldNotGetShopListException.class)
  public void shouldReturn_BadRequestError() throws URISyntaxException {
    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:9999/random-shop/compras?quantidade=10")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.BAD_REQUEST)
            .body("Invalid Parameters"));

    shopClient.getCompras(10);
  }

  @Test(expected = CouldNotGetShopListException.class)
  public void shouldReturn_InternalServerError() throws URISyntaxException {
    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:9999/random-shop/compras?quantidade=10")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Internal Server Error"));

    shopClient.getCompras(10);
  }

}
*/