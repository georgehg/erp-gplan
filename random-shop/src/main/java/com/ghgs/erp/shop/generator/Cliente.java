package com.ghgs.erp.shop.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Cliente {
  private final Integer id;
  private final String nome;
}
