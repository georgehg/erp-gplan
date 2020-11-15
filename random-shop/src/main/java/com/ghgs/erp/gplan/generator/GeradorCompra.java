package com.ghgs.erp.gplan.generator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component()
public class GeradorCompra {

  private static final List<Cliente> clientes = Arrays.asList(
      Cliente.of(1, "João"),
      Cliente.of(2, "Maria"),
      Cliente.of(3, "Pedro"),
      Cliente.of(4, "Mateus"),
      Cliente.of(5, "Lucas"),
      Cliente.of(6, "Isabel"),
      Cliente.of(7, "Tomás"),
      Cliente.of(8, "Felipe"),
      Cliente.of(9, "Marcus"),
      Cliente.of(10, "Paulo"));

  private static final long startSeconds = Instant.now().getEpochSecond();
  private static final long endSeconds = Instant.now().plus(Period.ofDays(7)).getEpochSecond();

  private final ThreadLocalRandom randomizer = ThreadLocalRandom.current();

  public List<Compra> geraCompra(int quantidade) {

    final List<Double> valores = randomizer.doubles(quantidade, 10, 100).boxed().collect(toList());
    final List<Integer> idClientes = randomizer.ints(quantidade, 0, 10).boxed().collect(toList());

    return IntStream.range(0, quantidade)
        .mapToObj(idx -> Compra.builder()
            .cliente(clientes.get(idClientes.get(idx)))
            .valor(valores.get(idx))
            .data(getRandomLocalDate())
            .qtyItens(randomizer.nextInt(1, 10))
            .build())
        .collect(toList());
  }

  private LocalDate getRandomLocalDate() {
    return LocalDateTime.ofEpochSecond(randomizer.nextLong(startSeconds, endSeconds), 0, ZoneOffset.UTC).toLocalDate();
  }

}
