package br.com.zupacademy.matheus.casadocodigo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private VerificaSePaisPossuiEstadosValidator verificaSePaisPossuiEstadosValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(verificaSePaisPossuiEstadosValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Long> cadastrar(@RequestBody @Valid ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toModel(manager);
        manager.persist(cliente);
        return ResponseEntity.ok(cliente.getId());
    }
}
