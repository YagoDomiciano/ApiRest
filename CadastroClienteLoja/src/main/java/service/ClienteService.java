package service;

import model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service

public class ClienteService {
    ArrayList<Cliente> clientes = new ArrayList<>();
    private long contador = 1;

    public ArrayList<Cliente> listar(){
        return this.clientes;
    }

    public Cliente criar(Cliente cliente){
        cliente.setId(contador);
        contador++; // prepara para a próxima inserção
        this.clientes.add(cliente);
        return cliente;
    }

    public Cliente buscar(Long id){
        for(int i=0; i < clientes.size(); i++){
            if (clientes.get(i).getId().equals(id)){
                this.clientes.get(i);
            }
        }
        return null;
    }

    public boolean remover(Long id){
        // removeIf faz o for, verificando se o id é igual ao id do array
        // se for igual, ele remove e retorna true, se não remover, retorna false
        return clientes.removeIf( c -> c.getId().equals(id));
    }

    public Cliente atualizar(Long id, Cliente novo){
        novo.setId(id);
        for(int i=0;i<clientes.size();i++){
            if (clientes.get(i).getId().equals(id)){ // encontrou
                clientes.set(i, novo); // atualiza o produto na lista
                return novo; // retorna o novo produto
            }
        }
        return null; // não encontrou
    }
}
