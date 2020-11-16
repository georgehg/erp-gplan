package com.ghgs.erp.bots.client;

import java.util.List;

public interface IShopClient {

  List<CompraDto> getCompras(Integer quantidade);

}
