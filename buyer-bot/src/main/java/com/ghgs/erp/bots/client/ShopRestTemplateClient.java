package com.ghgs.erp.bots.client;

import com.ghgs.erp.bots.exception.CouldNotGetShopListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//@Service
public class ShopRestTemplateClient implements ShopServiceApi {

    @Value("${clients.shop.port}")
    private String shopClientPort;

    @Value("${clients.shop.host}")
    private String shopClientHost;

    @Value("${clients.shop.url}")
    private String shopClientUrl;

    @Value("${clients.shop.api.generate-shops}")
    private String shopClientApi;

    @Autowired
    private RestTemplate restTemplate;

    public List<CompraDto> getCompras(Integer quantidade) {
        try {
            String endPoint = shopClientHost + ":" + shopClientPort + shopClientUrl + String.format(shopClientApi, quantidade);
            ResponseEntity<CompraDto[]> comprasResponse = restTemplate.getForEntity(endPoint, CompraDto[].class, quantidade);
            return Arrays.asList(comprasResponse.getBody());

        } catch (HttpClientErrorException ex) {
            throw new CouldNotGetShopListException("Error getting shop list with result " + ex);

        } catch (HttpServerErrorException ex) {
            throw new CouldNotGetShopListException("Random Shop service unavailable: " + ex);

        }
    }

}
