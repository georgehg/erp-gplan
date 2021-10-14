package com.ghgs.erp.bots.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("shop-storage")
public interface StorageFeignApi {

    @PostMapping(path = "/api/v1/shop-store/compras", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardaCompras(@RequestBody List<CompraDto> dto);

}
