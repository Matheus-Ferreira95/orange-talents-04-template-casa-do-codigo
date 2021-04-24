package br.com.zupacademy.matheus.casadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProibeNomeCategoriaDuplicadoValidator validator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaResponse> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest) {
       Categoria categoria = categoriaRequest.toModel();
       manager.persist(categoria);
       return ResponseEntity.ok(new CategoriaResponse(categoria));
    }
}
