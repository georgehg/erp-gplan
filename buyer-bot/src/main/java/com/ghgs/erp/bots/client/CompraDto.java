package com.ghgs.erp.bots.client;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CompraDto {
  private final ClienteDto cliente;
  private final Double valor;
  private final LocalDate data;
  private final Integer qtyItens;

  protected CompraDto() {
    this(null, null, null, null);
  }

}
