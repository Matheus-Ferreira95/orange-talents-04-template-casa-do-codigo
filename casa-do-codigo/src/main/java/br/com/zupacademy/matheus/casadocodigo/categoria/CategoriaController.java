package br.com.zupacademy.matheus.casadocodigo.categoria;

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
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaResponse> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest) {
       Categoria categoria = categoriaRequest.toModel();
       manager.persist(categoria);
       return ResponseEntity.ok(new CategoriaResponse(categoria));
    }
}
