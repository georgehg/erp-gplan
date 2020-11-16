package com.ghgs.erp.bots.client;

import com.ghgs.erp.bots.exception.CouldNotGetShopListException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
class ShopClient implements IShopClient {

  @Value("${clients.shop.port}")
  private String shopClientPort;

  @Value("${clients.shop.host}")
  private String shopClientHost;

  @Value("${clients.shop.url}")
  private String shopClientUrl;

  @Value("${clients.shop.api.shops}")
  private String shopClientApi;

  @Autowired
  private RestTemplate restTemplate;

  public List<CompraDto> getCompras(Integer quantidade) {
    String endPoint = shopClientHost + ":" + shopClientPort + shopClientUrl + String.format(shopClientApi, quantidade);
    try {
      ResponseEntity<CompraDto[]> comprasResponse = restTemplate.getForEntity(endPoint, CompraDto[].class, quantidade);
      return Arrays.asList(comprasResponse.getBody());
    } catch (HttpClientErrorException ex) {
      throw new CouldNotGetShopListException("Error getting shop list with result " + ex);
    }

  }

}
