package com.ghgs.erp.bots.client;

import com.ghgs.erp.bots.exception.CouldNotPostShopListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@Service
public class StorageConsulClient implements StorageServiceApi {

    @Value("${clients.storage.url}")
    private String storageClientUrl;

    @Value("${clients.storage.api.store}")
    private String storageClientApi;

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient;

    public void guardaCompras(List<CompraDto> compraDtos) {
        /*try {
            ServiceInstance serviceInstance = discoveryClient.getInstances("SHOPSTORAGE")
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new CouldNotPostShopListException("Error resolving storage service location"));

            String endPoint = "http://" +
                    serviceInstance.getHost() + ":" + serviceInstance.getPort() +
                    storageClientUrl + storageClientApi;

            restTemplate.postForLocation(endPoint, compraDtos);

        } catch (HttpClientErrorException ex) {
            throw new CouldNotPostShopListException("Error sending shop list: " + ex);

        } catch (HttpServerErrorException ex) {
            throw new CouldNotPostShopListException("Storage service unavailable: " + ex);

        }*/
    }

}
