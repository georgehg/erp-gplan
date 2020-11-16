package com.ghgs.erp.storage.domain;

import com.ghgs.erp.storage.repository.ClienteEntity;
import com.ghgs.erp.storage.repository.ClienteRepository;
import com.ghgs.erp.storage.repository.CompraEntity;
import com.ghgs.erp.storage.repository.CompraRepository;
import com.ghgs.erp.storage.repository.GastoClienteDto;
import com.ghgs.erp.storage.repository.VendasPorDataDto;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ShopStorageHandler {

  private final ClienteRepository clienteRepository;

  private final CompraRepository compraRepository;

  public ShopStorageHandler(ClienteRepository clienteRepository, CompraRepository compraRepository) {
    this.clienteRepository = clienteRepository;
    this.compraRepository = compraRepository;
  }

  @Transactional
  public void armazenaCompras(List<CompraDto> compras) {
    compras.stream()
        .peek(compra -> salvaCliente(compra.getCliente()))
        .forEach(this::salvaCompra);
  }

  private void salvaCliente(ClienteDto clienteDto) {
    if (!clienteRepository.existsById(clienteDto.getId())) {
      clienteRepository.save(ClienteEntity.of(clienteDto.getId(), clienteDto.getNome()));
    }
  }

  private void salvaCompra(CompraDto compraDto) {
    compraRepository.save(CompraEntity.of(
        clienteRepository.getOne(compraDto.getCliente().getId()),
        compraDto.getValor(),
        compraDto.getData(),
        compraDto.getQtyItens()));
  }

  public List<GastoClienteDto> getTotalGastoCliente() {
    return compraRepository.consultaTotalGastoCliente();
  }

  public List<VendasPorDataDto> getQuantidadeVendas() {
    return compraRepository.consultaVendasPorData();
  }

}
