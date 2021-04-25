package br.com.zupacademy.matheus.casadocodigo.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroBuscaController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> buscar() {
        List<Livro> list = manager.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        return ResponseEntity.ok(list.stream().map(LivroResponse::new).collect(Collectors.toList()));
    }
}
