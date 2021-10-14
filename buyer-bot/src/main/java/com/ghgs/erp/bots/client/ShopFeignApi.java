package com.ghgs.erp.bots.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("random-shop")
public interface ShopFeignApi {

    @GetMapping("/api/v1/random-shop/compras")
    ResponseEntity<CompraDto[]> geraCompras(@RequestParam(value = "quantidade") Integer quantidade);

}
