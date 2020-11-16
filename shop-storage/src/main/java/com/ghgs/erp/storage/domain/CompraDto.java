package com.ghgs.erp.storage.domain;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompraDto {
  private final ClienteDto cliente;
  private final Double valor;
  private final LocalDate data;
  private final Integer qtyItens;

  protected CompraDto() {
    this(null, null, null, null);
  }

}
