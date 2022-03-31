package com.example.ProjetoModuloBD.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProdutoRequest {
    private String codigo;
    private String nome;
    private Double preco;
    private Integer qtdeDisponivel;
}
