package com.ghgs.erp.storage.repository;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "data")
@AllArgsConstructor
public class VendasPorDataDto {
  private final LocalDate data;
  private final Long qtyItens;
}
