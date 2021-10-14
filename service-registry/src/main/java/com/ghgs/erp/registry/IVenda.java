package com.ghgs.erp.registry;

public interface IVenda<P, S, C extends Cliente> {

    S fazVenda(P tipoPagamento, C cliente);

}
