package com.example.ProjetoModuloBD.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "qtde_disponivel")
    private Integer qtde_disponivel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "compra_produto",
            joinColumns = { @JoinColumn(name = "id_produto") },
            inverseJoinColumns = { @JoinColumn(name = "id_compra") }
    )
    private List<Compra> compra;

}
