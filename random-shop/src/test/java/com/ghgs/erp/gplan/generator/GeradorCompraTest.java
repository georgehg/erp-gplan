package com.ghgs.erp.gplan.generator;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeradorCompraTest {

  @Test
  public void shouldGenerateRandomBuys_FiveBuys() {
    GeradorCompra geradorCompra = new GeradorCompra();
    List<Compra> compras = geradorCompra.geraCompra(5);
    assertGeracaoCompras(compras, 5);
  }

  @Test
  public void shouldGenerateRandomBuys_TenBuys() {
    GeradorCompra geradorCompra = new GeradorCompra();

    List<Compra> compras = geradorCompra.geraCompra(10);
    assertGeracaoCompras(compras, 10);
  }

  @Test
  public void shouldGenerateRandomBuys_25Buys() {
    GeradorCompra geradorCompra = new GeradorCompra();

    List<Compra> compras = geradorCompra.geraCompra(25);
    assertGeracaoCompras(compras, 25);
  }

  private void assertGeracaoCompras(List<Compra> compras, int quantidade) {
    final LocalDate now = LocalDate.now();
    final LocalDate week = now.plus(Period.ofDays(7));

    assertEquals(quantidade, compras.size());
    assertTrue(compras.get(0).getData().isEqual(now) || compras.get(0).getData().isAfter(now));
    assertTrue(compras.get(4).getData().isEqual(week) || compras.get(4).getData().isBefore(week));
  }

}
