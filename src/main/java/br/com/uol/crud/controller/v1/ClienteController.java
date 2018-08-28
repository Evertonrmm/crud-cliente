package br.com.uol.crud.controller.v1;

import br.com.uol.crud.expections.BusinessErrorException;
import br.com.uol.crud.model.Cliente;
import br.com.uol.crud.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private ClienteService service;

    @Autowired
    private ClienteController(ClienteService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(cliente, request.getRemoteAddr()));
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente){
        if(cliente.getId() == null){
            throw new BusinessErrorException("Request incorreto.");
        }

        return ResponseEntity.ok(service.atualizar(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
