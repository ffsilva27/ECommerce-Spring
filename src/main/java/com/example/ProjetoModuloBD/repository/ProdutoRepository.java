package com.example.ProjetoModuloBD.repository;

import com.example.ProjetoModuloBD.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {

    Produto findByCodigo(String codigo);

    List<Produto> findAllByCodigoIn(List<String> codigos);

    @Query("SELECT sum(preco) from produto where codigo in :produtos")
    Double sumPrecos(List<String> produtos);

}
