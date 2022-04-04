package com.example.ProjetoModuloBD.dto;

import com.example.ProjetoModuloBD.model.Compra;
import com.example.ProjetoModuloBD.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CompraResponse {

    private LocalDateTime data_compra;
    private String cpf_cliente;
    private Double valor_total_compra;
    private List<ProdutoResponse> produtos;

    public static CompraResponse convert(Compra compra){
        CompraResponse compraReturn = new CompraResponse();
        compraReturn.setData_compra(compra.getData_compra());
        compraReturn.setCpf_cliente(compra.getCpf());
        compraReturn.setValor_total_compra(compra.getValor_total_compra());
        compraReturn.setProdutos(ProdutoResponse.convert(compra.getProdutos()));
        return compraReturn;
    }

}
