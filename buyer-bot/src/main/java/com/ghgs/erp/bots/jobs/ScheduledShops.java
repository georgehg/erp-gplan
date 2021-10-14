package com.ghgs.erp.bots.jobs;

import com.ghgs.erp.bots.client.CompraDto;
import com.ghgs.erp.bots.client.ShopServiceApi;
import com.ghgs.erp.bots.client.StorageServiceApi;
import com.ghgs.erp.bots.exception.CouldNotPostShopListException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ScheduledShops {

    @Value("${jobs.shops.quantity}")
    private Integer shopsQuantity;

    private final ShopServiceApi shopServiceApi;

    private final StorageServiceApi storageServiceApi;

    public ScheduledShops(ShopServiceApi shopServiceApi, StorageServiceApi storageServiceApi) {
        this.shopServiceApi = shopServiceApi;
        this.storageServiceApi = storageServiceApi;
    }

    @Scheduled(cron = "${jobs.schedule.cron-expression}")
    public void randomShops() throws CouldNotPostShopListException {
        try {
            log.info("Starting shopping job");
            List<CompraDto> compras = shopServiceApi.getCompras(shopsQuantity);
            log.info("Retrieved {} shops", compras.size());
            log.info("Shops: {}", compras);

            storageServiceApi.guardaCompras(compras);
            log.info("Shops stored");
        } catch (Exception ex) {
            log.error("Job stopped wit error", ex);
        }

        log.info("Job finished");
    }

}
