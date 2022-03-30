package com.example.ProjetoModuloBD.controller;

import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.model.Produto;
import com.example.ProjetoModuloBD.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public Page<ProdutoResponse> getAll(){
        Pageable pageable = PageRequest.of(0,5);
        List<ProdutoResponse> produtoResponses = ProdutoResponse.convert(produtoService.findAll());
        Page<ProdutoResponse> produtoReturn = new PageImpl<ProdutoResponse>(produtoResponses, pageable, produtoResponses.size());
        return produtoReturn;
    }

    @GetMapping("/codigo")
    public Page<ProdutoResponse> getProductByCode(@RequestParam(value = "cod") String codigo){
        Pageable pageable = PageRequest.of(0,5);
        List<ProdutoResponse> produtoResponses = ProdutoResponse.convert(produtoService.findByCodigo(codigo));
        Page<ProdutoResponse> produtoReturn = new PageImpl<ProdutoResponse>(produtoResponses, pageable, produtoResponses.size());
        return produtoReturn;
    }

}
