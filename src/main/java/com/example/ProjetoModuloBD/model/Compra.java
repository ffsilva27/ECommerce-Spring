package com.example.ProjetoModuloBD.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_compra")
    private LocalDateTime data_compra;

    @Column(name = "cpf_cliente")
    private String cpf;

    @Column(name = "valor_total_compra")
    private Double valor_total_compra;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "compra_produto",
            joinColumns = { @JoinColumn(name = "id_compra") },
            inverseJoinColumns = { @JoinColumn(name = "id_produto") }
    )
    private List<Produto> produtos;

}
