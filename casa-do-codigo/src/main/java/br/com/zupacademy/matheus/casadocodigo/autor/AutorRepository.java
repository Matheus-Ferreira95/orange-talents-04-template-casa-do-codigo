package br.com.zupacademy.matheus.casadocodigo.autor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {

    Autor findByEmail(String email);
}
