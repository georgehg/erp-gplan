package com.ghgs.erp.storage.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteDto {
  private final Integer id;
  private final String nome;

  protected ClienteDto() {
    this(null, null);
  }

}
