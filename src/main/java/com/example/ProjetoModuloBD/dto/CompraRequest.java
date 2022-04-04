package com.example.ProjetoModuloBD.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class CompraRequest {
    private LocalDateTime data;
    private String cpf;
    private List<String> produtos;
}
