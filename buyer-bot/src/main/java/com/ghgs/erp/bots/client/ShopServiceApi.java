package com.ghgs.erp.bots.client;

import java.util.List;

public interface ShopServiceApi {

    List<CompraDto> getCompras(Integer quantidade);

}
