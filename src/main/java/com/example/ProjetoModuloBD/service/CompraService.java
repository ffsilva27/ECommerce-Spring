package com.example.ProjetoModuloBD.service;

import com.example.ProjetoModuloBD.dto.CompraRequest;
import com.example.ProjetoModuloBD.dto.CompraResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.model.Compra;
import com.example.ProjetoModuloBD.repository.CompraRepository;
import com.example.ProjetoModuloBD.repository.specification.CompraSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoService produtoService;

    public Page<CompraResponse> listByCPF(String cpf, Pageable pageable) {
        Specification<Compra> specification = Specification.where(null);
        if (cpf != null) {
            specification = Specification.where(CompraSpecification.filterOneByCpf(cpf));
        }
        return compraRepository
                .findAll(specification, pageable)
                .map(CompraResponse::convert);
    }

    public CompraResponse createCompra(CompraRequest compraRequest) throws BadRequest {
        Compra compra = new Compra();
        compra.setData_compra(compraRequest.getData());
        compra.setCpf(compraRequest.getCpf());
        compra.setProdutos(produtoService.findAllProductsWithCode(compraRequest.getProdutos()));
        compra.setValor_total_compra(produtoService.sumValor(compraRequest.getProdutos()));

        if(compra.getValor_total_compra() == null){
            throw new BadRequest("O campo produtos deve ser preenchido.");
        }

        compraRepository.save(compra);

        return CompraResponse.convert(compra);
    }
}
