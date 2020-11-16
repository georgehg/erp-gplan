package com.ghgs.erp.storage.rest;

import com.ghgs.erp.storage.domain.CompraDto;
import com.ghgs.erp.storage.domain.ShopStorageService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop-store")
public class ShopStorageController {

  private final ShopStorageService shopStorageService;

  public ShopStorageController(ShopStorageService shopStorageService) {
    this.shopStorageService = shopStorageService;
  }

  @PostMapping(value = "/compras", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity guardaCompras(@RequestBody List<CompraDto> dto) {
    shopStorageService.armazenaCompra(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
