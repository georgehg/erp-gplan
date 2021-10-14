package com.ghgs.erp.bots.client;

import com.ghgs.erp.bots.exception.CouldNotPostShopListException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

//@Service
public class StorageRestTemplateClient implements StorageServiceApi {

    @Value("${clients.storage.port}")
    private String storageClientPort;

    @Value("${clients.storage.host}")
    private String storageClientHost;

    @Value("${clients.storage.url}")
    private String storageClientUrl;

    @Value("${clients.storage.api.store}")
    private String storageClientApi;

    @Autowired
    private RestTemplate restTemplate;

    public void guardaCompras(List<CompraDto> compraDtos) {
        try {
            String endPoint = storageClientHost + ":" + storageClientPort + storageClientUrl + storageClientApi;
            restTemplate.postForLocation(endPoint, compraDtos);

        } catch (HttpClientErrorException ex) {
            throw new CouldNotPostShopListException("Error sending shop list: " + ex);

        } catch (HttpServerErrorException ex) {
            throw new CouldNotPostShopListException("Storage service unavailable: " + ex);

        }
    }

}
