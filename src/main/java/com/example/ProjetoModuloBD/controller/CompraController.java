package com.example.ProjetoModuloBD.controller;

import com.example.ProjetoModuloBD.dto.CompraRequest;
import com.example.ProjetoModuloBD.dto.CompraResponse;
import com.example.ProjetoModuloBD.dto.ProdutoRequest;
import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compra")
public class CompraController {

    private final CompraService compraService;

    @GetMapping()
    public Page<CompraResponse> listCompras(
            @RequestParam(name = "cpf", required = false) String cpf,
            Pageable pageable
    ) {
        return compraService.listByCPF(cpf, pageable);
    }

    @PostMapping()
    public CompraResponse createProduct(@RequestBody CompraRequest compraRequest) throws BadRequest {
        return compraService.createCompra(compraRequest);
    }

}
