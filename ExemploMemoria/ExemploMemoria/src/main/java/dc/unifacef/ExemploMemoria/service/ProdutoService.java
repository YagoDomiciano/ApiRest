package dc.unifacef.ExemploMemoria.service;

import dc.unifacef.ExemploMemoria.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProdutoService {
    // vetor de produtos
    ArrayList<Produto> produtos = new ArrayList<>();
    // contador
    private long contador = 1;
    public ArrayList<Produto> listar(){
        return this.produtos;
    }

    public Produto criar(Produto produto){
        produto.setId(contador);
        contador++; // prepara para a próxima inserção
        this.produtos.add(produto);
        return produto;
    }

    public boolean remove(Long id){
        // removeIf faz o for, verificando se o id é igual ao id do array
        // se for igual, ele remove e retorna true, se não remover, retorna false
        return produtos.removeIf( p -> p.getId().equals(id));
    }

    public Produto atualiza(Long id, Produto novo){
        novo.setId(id);
        for(int i=0;i<produtos.size();i++){
            if (produtos.get(i).getId().equals(id)){ // encontrou
                produtos.set(i, novo); // atualiza o produto na lista
                return novo; // retorna o novo produto
            }
        }
        return null; // não encontrou
    }
}
