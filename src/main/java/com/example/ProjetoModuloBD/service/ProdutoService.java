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
import java.util.Random;


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
        String productCode = this.createProductCode();
        Produto produtoVerificado = findByCodigoRaiz(productCode);

        while (produtoVerificado != null){
            productCode = this.createProductCode();
            produtoVerificado = findByCodigoRaiz(productCode);
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
}
