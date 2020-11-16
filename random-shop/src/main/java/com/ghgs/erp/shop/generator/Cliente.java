package com.ghgs.erp.shop.generator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public class Cliente {
  private final Integer id;
  private final String nome;
}
