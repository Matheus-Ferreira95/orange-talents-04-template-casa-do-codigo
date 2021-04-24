package br.com.zupacademy.matheus.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    /*
    @Autowired
    private ProibeEmailDuplicadoValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder                                                              um método publico com essa anotação logo no primeiro request que é feito
    public void init(WebDataBinder binder) {                                 no controller, o código do init é executado para realizar as configs
          binder.addValidators(proibeEmailDuplicadoAutorValidator);          na execução da request relativa a este controller
    }
    */

    @PostMapping
    @Transactional
    public ResponseEntity<AutorResponse> cadastrar(@RequestBody @Valid AutorRequest autorRequest) {
        Autor autor = autorRequest.toModel();
        manager.persist(autor);
        return ResponseEntity.ok(new AutorResponse(autor));
    }
}
