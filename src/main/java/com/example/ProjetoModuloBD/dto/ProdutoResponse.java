package com.example.ProjetoModuloBD.dto;

import com.example.ProjetoModuloBD.model.Compra;
import com.example.ProjetoModuloBD.model.CompraProduto;
import com.example.ProjetoModuloBD.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProdutoResponse {

    //Não vi necessidade de retornar a lista de compras que esta no model de produtos.
    private String codigo;
    private String nome;
    private Double preco;
    private Integer qtde_disponivel;

    public ProdutoResponse(Produto produto){
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.qtde_disponivel = produto.getQtde_disponivel();
    }

    public static List<ProdutoResponse> convert(List<Produto> produto){
        return produto.stream().map(ProdutoResponse::new).collect(Collectors.toList());
    }

    public static ProdutoResponse convert(CompraProduto produto){
        ProdutoResponse produtoResponse = new ProdutoResponse();

        produtoResponse.setCodigo(produto.getProduto().getCodigo());
        produtoResponse.setNome(produto.getProduto().getNome());
        produtoResponse.setPreco(produto.getProduto().getPreco());
        produtoResponse.setQtde_disponivel(produto.getProduto().getQtde_disponivel());
        return produtoResponse;
    }
}
