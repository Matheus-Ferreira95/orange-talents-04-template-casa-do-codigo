package br.com.zupacademy.matheus.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDuplicadoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {  // qual é o tipo de parametro que a gente vai aplicar a validação
        return AutorRequest.class.isAssignableFrom(aClass); // para ver se a classe do argumento é a mesma ou filha do autorRequest
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        AutorRequest request = (AutorRequest) o; // nossa validação só vai ser executada se não houver nenhum outro erro de validação(padrão)

        Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());
        if (possivelAutor.isPresent()) {
            errors.rejectValue("email", null, "Já existe um(a) outro(a) autor(a) com o mesmo email "
                                                                                         + request.getEmail());
        }
    }
}
