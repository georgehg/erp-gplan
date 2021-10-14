package com.ghgs.erp.bots.client;

import com.ghgs.erp.bots.exception.CouldNotGetShopListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.List;

@Service
public class ShopFeignClient implements ShopServiceApi {

    @Autowired
    private ShopFeignApi shopFeignApi;

    @Override
    public List<CompraDto> getCompras(Integer quantidade) {
        try {
            ResponseEntity<CompraDto[]> randomCompras = shopFeignApi.geraCompras(quantidade);
            return Arrays.asList(randomCompras.getBody());
        } catch (HttpClientErrorException ex) {
            throw new CouldNotGetShopListException("Error getting shop list with result " + ex);

        } catch (HttpServerErrorException ex) {
            throw new CouldNotGetShopListException("Random Shop service unavailable: " + ex);

        }
    }

}
