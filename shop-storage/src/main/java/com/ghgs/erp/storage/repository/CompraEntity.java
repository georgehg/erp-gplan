package com.ghgs.erp.storage.repository;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true)
@Entity
@Table(name = "table_compras")
public class CompraEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private final Long id;
  @ManyToOne
  private final ClienteEntity cliente;
  private final Double valor;
  private final LocalDate data;
  private final Integer qtyItens;
}
