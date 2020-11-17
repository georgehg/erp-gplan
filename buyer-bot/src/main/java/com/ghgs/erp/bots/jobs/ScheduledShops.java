package com.ghgs.erp.bots.jobs;

import com.ghgs.erp.bots.client.CompraDto;
import com.ghgs.erp.bots.client.ShopClient;
import com.ghgs.erp.bots.client.StorageClient;
import com.ghgs.erp.bots.exception.CouldNotPostShopListException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledShops {

  @Value("${jobs.shops.quantity}")
  private Integer shopsQuantity;

  private final ShopClient shopClient;

  private final StorageClient storageClient;

  public ScheduledShops(ShopClient shopClient, StorageClient storageClient) {
    this.shopClient = shopClient;
    this.storageClient = storageClient;
  }

  @Scheduled(cron = "${jobs.schedule.cron-expression}")
  public void randomShops() throws CouldNotPostShopListException {
    try {
      log.info("Starting shopping job");
      List<CompraDto> compras = shopClient.getCompras(shopsQuantity);
      log.info("Retrieved {} shops", compras.size());
      log.debug("Shops: {}", compras);

      storageClient.guardaCompras(compras);
      log.info("Shops stored");
    } catch (Exception ex) {
      log.error("Job stopped wit error", ex);
    }

    log.info("Job finished");
  }

}
