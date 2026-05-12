package dc.unifacef.ExemploMemoria.controller;

import dc.unifacef.ExemploMemoria.model.Produto;
import dc.unifacef.ExemploMemoria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController // classe responsável por controlar requisições rest
@RequestMapping("/produto") // response apenas solicitações do endpoint /produto
public class ProdutoController {

    // injeção de dependência
    // permite chamar métodos com objetos não instanciados
    @Autowired
    ProdutoService service;

    // requisição pelo verbo GET
    @GetMapping
    public ResponseEntity<ArrayList<Produto>> listar(){
        // retorna OK ao cliente
        // retorna a lista de produtos
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto){
        Produto novo = service.criar(produto);
        URI uri = URI.create("/produto/" + novo.getId());
        return  ResponseEntity.created(uri).body(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        if (service.remove(id)){
            return ResponseEntity.noContent().build(); // removeu
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualiza(@PathVariable Long id,
                                            @RequestBody Produto novo){
        Produto resposta = service.atualiza(id, novo);
        // se atualizou o produto, retorna ele, senão retorna notfound
        return (resposta != null) ? ResponseEntity.ok(resposta) :
                ResponseEntity.notFound().build();
    }
}
