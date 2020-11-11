package com.ghgs.erp.gplan.generator;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Compra {
  private final Cliente cliente;
  private final Double valor;
  private final LocalDate data;
  private final Integer qtyItens;
}
