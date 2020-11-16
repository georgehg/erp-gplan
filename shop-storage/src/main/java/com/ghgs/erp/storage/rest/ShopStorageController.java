package com.ghgs.erp.storage.rest;

import com.ghgs.erp.storage.domain.CompraDto;
import com.ghgs.erp.storage.domain.ShopStorageHandler;
import com.ghgs.erp.storage.repository.GastoClienteDto;
import com.ghgs.erp.storage.repository.VendasPorDataDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop-store")
public class ShopStorageController {

  private final ShopStorageHandler shopStorageHandler;

  public ShopStorageController(ShopStorageHandler shopStorageHandler) {
    this.shopStorageHandler = shopStorageHandler;
  }

  @PostMapping(path = "/compras", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity guardaCompras(@RequestBody List<CompraDto> dto) {
    shopStorageHandler.armazenaCompras(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(path = "/reports/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<GastoClienteDto>> reportaTotalGastoCliente() {
    return ResponseEntity.ok(shopStorageHandler.getTotalGastoCliente());
  }

  @GetMapping(path = "/reports/vendas", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<VendasPorDataDto>> reportaTotalVendasPorData() {
    return ResponseEntity.ok(shopStorageHandler.getQuantidadeVendas());
  }

}
