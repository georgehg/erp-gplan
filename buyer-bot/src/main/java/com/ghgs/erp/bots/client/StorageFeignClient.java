package com.ghgs.erp.bots.client;

import com.ghgs.erp.bots.exception.CouldNotPostShopListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class StorageFeignClient implements StorageServiceApi {

    @Autowired
    private StorageFeignApi storageFeignApi;

    public void guardaCompras(List<CompraDto> compraDtos) {
        try {
            storageFeignApi.guardaCompras(compraDtos);
        } catch (HttpClientErrorException ex) {
            throw new CouldNotPostShopListException("Error sending shop list: " + ex);

        } catch (HttpServerErrorException ex) {
            throw new CouldNotPostShopListException("Storage service unavailable: " + ex);
        }
    }

}
