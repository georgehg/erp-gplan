package com.ghgs.erp.shop.rest;

import com.ghgs.erp.shop.generator.Compra;
import com.ghgs.erp.shop.generator.GeradorCompra;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random-shop")
public class RandomShopController {

  private final GeradorCompra geradorCompra;

  public RandomShopController(GeradorCompra geradorCompra) {
    this.geradorCompra = geradorCompra;
  }

  @GetMapping(path = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Compra>> geraCompras(@RequestParam(value = "quantidade") Integer quantidade) {
    return ResponseEntity.ok(geradorCompra.geraCompra(quantidade));
  }

}
