package com.example.ProjetoModuloBD.service;

import com.example.ProjetoModuloBD.dto.ProdutoRequest;
import com.example.ProjetoModuloBD.dto.ProdutoResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.exceptions.NotFound;
import com.example.ProjetoModuloBD.model.Produto;
import com.example.ProjetoModuloBD.repository.ProdutoRepository;
import com.example.ProjetoModuloBD.repository.specification.ProdutoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Page<ProdutoResponse> listByCodigo(String codigo, Pageable pageable) {
        Specification<Produto> specification = Specification.where(null);
        if (codigo != null) {
            specification = Specification.where(ProdutoSpecification.filterOneByCodigo(codigo));
        }
        return produtoRepository
                .findAll(specification, pageable)
                .map(x -> new ProdutoResponse(x));
    }

    public Produto findByCodigo(String codigo){
        return produtoRepository.findByCodigo(codigo);
    }

    public ProdutoResponse createProduct(ProdutoRequest produtoRequest) {
        String productCode = this.createProductCode();
        Produto produtoVerificado = findByCodigo(productCode);

        while (produtoVerificado != null){
            productCode = this.createProductCode();
            produtoVerificado = findByCodigo(productCode);
        }

        Produto produto = new Produto();
        produto.setCodigo(productCode);
        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());
        produto.setQtde_disponivel(produtoRequest.getQtdeDisponivel());

        return new ProdutoResponse(produtoRepository.save(produto));

    }

    public String createProductCode(){
        Random ra = new Random();
        Character prefixo = (char) (ra.nextInt(26) + 'A');
        Integer nAleatorio = ra.nextInt(999);
        String sufixo = "";
        if(nAleatorio>=0 && nAleatorio<=9){
            sufixo = "00" + Integer.toString(nAleatorio);
        }else if(nAleatorio>=10 && nAleatorio<=99){
            sufixo = "0" + Integer.toString(nAleatorio);
        }else{
            sufixo = Integer.toString(nAleatorio);
        }
        return  prefixo + sufixo;
    }

    public List<Produto> findAllProductsWithCode(List<String> produtos) {
        return produtoRepository.findAllByCodigoIn(produtos);
    }

    public Double sumValor(List<String> codigos) {
        return produtoRepository.sumPrecos(codigos);
    }
}