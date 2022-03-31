package com.example.ProjetoModuloBD.controller;

import com.example.ProjetoModuloBD.dto.ProdutoRequest;
import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.exceptions.NotFound;
import com.example.ProjetoModuloBD.model.Produto;
import com.example.ProjetoModuloBD.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        Page<ProdutoResponse> produtoReturn = produtoService.findAll();
        if(produtoReturn.isEmpty()){
            return new ResponseEntity<>(produtoReturn, HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(produtoReturn);
        }
    }

    @GetMapping("/codigo")
    public ResponseEntity<Object> getProductByCode(@RequestParam(value = "cod") String codigo) throws NotFound {
        ProdutoResponse produtoReturn = produtoService.findByCodigo(codigo);
        return ResponseEntity.ok(produtoReturn);
    }

    @PostMapping
    public ProdutoResponse createProduct(@RequestBody ProdutoRequest produtoRequest) throws BadRequest {
        return produtoService.createProduct(produtoRequest);
    }

}
