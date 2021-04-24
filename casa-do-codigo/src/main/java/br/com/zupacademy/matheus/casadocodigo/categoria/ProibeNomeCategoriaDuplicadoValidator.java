package br.com.zupacademy.matheus.casadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeCategoriaDuplicadoValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        CategoriaRequest categoriaRequest = (CategoriaRequest) o;
        Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(categoriaRequest.getNome());
        if (possivelCategoria.isPresent()) {
            errors.rejectValue("nome", null, "Já existe uma categoria cadastrada com o nome "
                    + categoriaRequest.getNome());
        }
    }
}
