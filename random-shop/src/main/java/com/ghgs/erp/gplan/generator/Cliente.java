package com.ghgs.erp.gplan.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class Cliente {
  private final Integer id;
  private final String nome;
}
