package br.com.zupacademy.matheus.casadocodigo.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProibeNomeDuplicadoParaOMesmoPaisValidator validation;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validation);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid EstadoRequest estadoRequest) {
        Estado estado = estadoRequest.toModel(manager);
        manager.persist(estado);
        return ResponseEntity.ok(estado.toString());
    }
}
