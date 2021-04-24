package br.com.zupacademy.matheus.casadocodigo.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<AutorResponse> cadastrar(@RequestBody @Valid AutorRequest autorRequest) {
        Autor autor = autorRequest.toModel();
        manager.persist(autor);
        return ResponseEntity.ok(new AutorResponse(autor));
    }
}
