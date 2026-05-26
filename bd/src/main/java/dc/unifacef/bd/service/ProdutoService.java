package dc.unifacef.bd.service;

import dc.unifacef.bd.model.Produto;
import dc.unifacef.bd.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    // essa classe vai usar o ProdutoRepository explorando a injeção
    // de dependência pelo construtor
    private ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }
    // recupera todos os produtos
    public List<Produto> listar(){
        return repo.findAll();
    }
    // recupera um produto por id
    public Optional<Produto> buscarPorId(Long id){
        return repo.findById(id);
    }

    public Produto salvar(Produto produto){
        return repo.save(produto); //vai inserir pois produto nn tem id
    }

    public boolean remover(Long id){
        if(repo.existsById(id)){
            //produto existe no banco
            repo.deleteById(id);
            return true;
        }
        return false;
    }
    public  Produto atualizar(Long id, Produto atual){
        if (repo.existsById(id)){
            atual.setId(id);
            repo.save(atual); //vai atualizar pois o metodo atual nn tem id
        }
        return null;
    }

}
