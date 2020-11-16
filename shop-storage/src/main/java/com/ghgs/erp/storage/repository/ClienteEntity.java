package com.ghgs.erp.storage.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "table_clientes")
public class ClienteEntity {
  @Id
  private final Integer id;
  private final String nome;
}
