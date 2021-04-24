package br.com.zupacademy.matheus.casadocodigo.validation;

import br.com.zupacademy.matheus.casadocodigo.autor.Autor;
import br.com.zupacademy.matheus.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.matheus.casadocodigo.autor.AutorRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, AutorRequest> {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean isValid(AutorRequest request, ConstraintValidatorContext context) {
        Autor autor = autorRepository.findByEmail(request.getEmail());

        if (autor != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email já cadastrado, informe outro email.")
                    .addPropertyNode("email")
                    .addConstraintViolation();
        }

        return autor == null;
    }
}