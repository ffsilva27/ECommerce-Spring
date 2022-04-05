package com.example.ProjetoModuloBD.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class CompraRequest {
    @NotNull
    private LocalDateTime data;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private List<String> produtos;
}
