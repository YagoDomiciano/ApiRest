package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ClienteService;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.ArrayList;
import model.Cliente;

@RestController
@RequestMapping("/cliente")

public class ClienteController {
    @Autowired
    ClienteService service;

    @GetMapping
    public ResponseEntity<ArrayList<Cliente>> listar(){
        // retorna OK ao cliente
        // retorna a lista de produtos
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente){
        Cliente novo = service.criar(cliente);
        URI uri = URI.create("/cliente/" + novo.getId());
        return  ResponseEntity.created(uri).body(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        if (service.remover(id)){
            return ResponseEntity.noContent().build(); // removeu
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente atualizado = service.atualizar(id, cliente);
            return ResponseEntity.ok(atualizado);
    }
}
