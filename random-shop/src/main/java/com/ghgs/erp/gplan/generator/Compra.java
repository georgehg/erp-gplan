package com.ghgs.erp.gplan.generator;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
public class Compra {
  private final Cliente cliente;
  private final Double valor;
  private final LocalDate data;
  private final Integer qtyItens;
}
