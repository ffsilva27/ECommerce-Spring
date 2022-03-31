package com.example.ProjetoModuloBD.service;

import com.example.ProjetoModuloBD.dto.ProdutoRequest;
import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.exceptions.NotFound;
import com.example.ProjetoModuloBD.model.Produto;
import com.example.ProjetoModuloBD.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Page<ProdutoResponse> findAll(){
        Pageable pageable = PageRequest.of(0,5);
        List<ProdutoResponse> produtoResponses = ProdutoResponse.convert(produtoRepository.findAll());
        Page<ProdutoResponse> produtoReturn = new PageImpl<ProdutoResponse>(produtoResponses, pageable, produtoResponses.size());
        return produtoReturn;
    }

    public Produto findByCodigoRaiz(String codigo){
        return produtoRepository.findByCodigo(codigo);
    }

    public ProdutoResponse findByCodigo(String codigo) throws NotFound {
        Produto produto = findByCodigoRaiz(codigo);
        if(produto == null){
            throw new NotFound("Produto não encontrado!");
        }else{
            ProdutoResponse produtoResponse = new ProdutoResponse(produto);
            return produtoResponse;
        }
    }

    public ProdutoResponse createProduct(ProdutoRequest produtoRequest) throws BadRequest {
        Produto produtoVerificado = findByCodigoRaiz(produtoRequest.getCodigo());
        if(produtoVerificado == null){
            Produto produto = new Produto();
            produto.setCodigo(produtoRequest.getCodigo());
            produto.setNome(produtoRequest.getNome());
            produto.setPreco(produtoRequest.getPreco());
            produto.setQtde_disponivel(produtoRequest.getQtdeDisponivel());

            return new ProdutoResponse(produtoRepository.save(produto));
        }else {
            throw new BadRequest("Código do produto já esta em uso.");
        }
    }
}
