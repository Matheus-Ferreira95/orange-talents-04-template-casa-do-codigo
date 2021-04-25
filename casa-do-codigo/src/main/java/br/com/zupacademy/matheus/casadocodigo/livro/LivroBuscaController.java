package br.com.zupacademy.matheus.casadocodigo.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroBuscaController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> buscar(Pageable pageable) {
        List<Livro> list = livroRepository.findAll();
        return ResponseEntity.ok(list.stream().map(LivroResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponsePorId> buscarPorId(@PathVariable Long id) {
        Optional<Livro> possivelLivro = livroRepository.findById(id);
        if (possivelLivro.isPresent()) {
            return ResponseEntity.ok(new LivroResponsePorId(possivelLivro.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
