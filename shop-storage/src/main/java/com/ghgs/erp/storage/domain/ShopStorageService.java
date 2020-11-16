package com.ghgs.erp.storage.domain;

import com.ghgs.erp.storage.repository.ClienteEntity;
import com.ghgs.erp.storage.repository.ClienteRepository;
import com.ghgs.erp.storage.repository.CompraEntity;
import com.ghgs.erp.storage.repository.CompraRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ShopStorageService {

  private final ClienteRepository clienteRepository;

  private final CompraRepository compraRepository;

  public ShopStorageService(ClienteRepository clienteRepository, CompraRepository compraRepository) {
    this.clienteRepository = clienteRepository;
    this.compraRepository = compraRepository;
  }

  @Transactional
  public void armazenaCompra(List<CompraDto> compras) {
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

}
