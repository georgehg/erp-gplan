package com.ghgs.erp.gplan.controller;

import com.ghgs.erp.gplan.generator.Compra;
import com.ghgs.erp.gplan.generator.GeradorCompra;
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

  @GetMapping(value = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Compra>> getCompras(@RequestParam(value = "quantidade") Integer quantidade) {
    return ResponseEntity.ok(geradorCompra.geraCompra(quantidade));
  }

}
