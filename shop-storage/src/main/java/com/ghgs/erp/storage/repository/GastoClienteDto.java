package com.ghgs.erp.storage.repository;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "idCliente")
@AllArgsConstructor
public class GastoClienteDto {
  private final Integer idCliente;
  private final String nome;
  private final Double valor;
}
