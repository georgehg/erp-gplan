package com.ghgs.erp.storage.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "table_clientes")
public class ClienteEntity {

  @Id
  private final Integer id;

  private final String nome;

  protected ClienteEntity() {
    this(null, null);
  }

}
