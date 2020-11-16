package com.ghgs.erp.storage.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {

  @Query("select new com.ghgs.erp.storage.repository.GastoClienteDto(compras.cliente.id, compras.cliente.nome, sum(compras.valor)) " +
      " from CompraEntity compras " +
      " group by compras.cliente.id, compras.cliente.nome ")
  List<GastoClienteDto> consultaTotalGastoCliente();

  @Query("select new com.ghgs.erp.storage.repository.VendasPorDataDto(compras.data, sum(compras.qtyItens)) " +
      " from CompraEntity compras " +
      " group by compras.data ")
  List<VendasPorDataDto> consultaVendasPorData();

}
