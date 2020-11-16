package com.ghgs.erp.bots.client;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
public class CompraDto {
  private final ClienteDto cliente;
  private final Double valor;
  private final LocalDate data;
  private final Integer qtyItens;
}
